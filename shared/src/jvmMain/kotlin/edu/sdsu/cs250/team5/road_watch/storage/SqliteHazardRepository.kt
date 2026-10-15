package edu.sdsu.cs250.team5.road_watch.storage

// HazardReport = the data model we store/return. HazardRepository = the interface we implement.
// HazardType = the enum of hazard categories. These live in the same project (commonMain).
import edu.sdsu.cs250.team5.road_watch.HazardReport
import edu.sdsu.cs250.team5.road_watch.HazardRepository
import edu.sdsu.cs250.team5.road_watch.HazardType

// java.sql = JDBC, the standard Java/JVM API for talking to databases.
// This is why this class is JVM-only (Android/iOS don't have java.sql).
import java.sql.DriverManager   // opens a connection to the database
import java.sql.ResultSet       // the "cursor" we read query results from


/**
 * Stores and retrieves hazard reports using SQLite.
 *
 * Call [initializeDatabase] with the same database URL before using this class.
 * Each operation opens and closes its own connection.
 * JDBC operations block, so callers should run them off the UI thread.
 *
 * @param databaseUrl database location; tests can supply a temporary file.
 */
class SqliteHazardRepository(

    // The database location. Defaults to a local file "roadwatch.db".
    // It's a constructor PARAMETER (not hardcoded) so tests can pass in a
    // throwaway temp database instead of hitting the real one.
    private val databaseUrl: String = "jdbc:sqlite:roadwatch.db"

) : HazardRepository {   // ": HazardRepository" = this class fulfills that interface's contract


    // The table name, stored in one place. The SQL statements below interpolate
    // this ($tableName). It's a fixed internal constant — never user input — so
    // putting it in the SQL string is safe (unlike user values, which use ?).
    private val tableName = "reports"


    /**
     * Validates and stores a report, then returns its database-assigned ID.
     * The description, type, and coordinates are stored unchanged.
     *
     * @throws IllegalArgumentException if the description is blank or
     * the coordinates are outside their valid ranges.
     */
    override fun addReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String?
    ): HazardReport {

        // ───────────────────────────────────────────────────────────
        // STEP 1 — VALIDATE FIRST (guard clauses)
        // Reject bad input up front, before we open the DB or write anything.
        // require(...) throws IllegalArgumentException if the condition is false.
        // ───────────────────────────────────────────────────────────

        // Description can't be blank (isNotBlank also rejects whitespace-only).
        require(description.isNotBlank()) {
            "Description must not be blank"
        }

        // Latitude must be a real coordinate: between -90 and 90.
        require(lat in -90.0..90.0) {
            "Latitude out of range: $lat"
        }

        // Longitude must be between -180 and 180.
        require(lng in -180.0..180.0) {
            "Longitude out of range: $lng"
        }


        // ───────────────────────────────────────────────────────────
        // STEP 2 — OPEN A CONNECTION
        // getConnection opens a line to the .db file.
        // .use { } runs the block, then GUARANTEES the connection is closed
        // afterward — even if something throws. (Kotlin's version of RAII,
        // since the JVM has no destructors.) 'return' hands back whatever
        // the .use block produces at the end (the HazardReport in STEP 5).
        // ───────────────────────────────────────────────────────────
        return DriverManager.getConnection(databaseUrl).use { connection ->


            // ───────────────────────────────────────────────────────
            // STEP 3 — INSERT THE ROW (safely)
            // prepareStatement builds the INSERT with ? placeholders instead
            // of gluing the values into the SQL text. This is what prevents
            // SQL injection: the values are sent separately and treated as
            // pure data, never as SQL commands.
            // ($tableName is safe here — it's our own constant, not user input.)
            // ───────────────────────────────────────────────────────
            connection.prepareStatement(
                """
                INSERT INTO $tableName (type, description, lat, lng, image)
                VALUES (?, ?, ?, ?, ?)
                """.trimIndent()
            ).use { statement ->   // .use closes the statement too

                // Fill each ? placeholder, in order (1-based):
                statement.setString(1, type.name)    // store the enum's name, e.g. "POTHOLE"
                statement.setString(2, description)
                statement.setDouble(3, lat)
                statement.setDouble(4, lng)

                // A null image reference becomes a real SQL NULL (not the text "null").
                statement.setString(5, image)

                // Actually run the write. executeUpdate is for INSERT/UPDATE/DELETE.
                statement.executeUpdate()
            }


            // ───────────────────────────────────────────────────────
            // STEP 4 — READ BACK THE GENERATED ID
            // We never set the id — the table's AUTOINCREMENT column makes SQLite
            // assign it. last_insert_rowid() asks SQLite what id it just handed out.
            // It MUST be on the SAME connection as the insert, because that value
            // is tracked per-connection.
            // ───────────────────────────────────────────────────────
            val id = connection.createStatement().use { statement ->
                statement.executeQuery("SELECT last_insert_rowid()").use { result ->

                    // result is a cursor; .next() steps onto the single row it returned.
                    // check(...) throws if there somehow isn't one (shouldn't happen).
                    check(result.next()) {
                        "Could not read the new report ID"
                    }

                    // Read column 1 of that row = the new id.
                    result.getLong(1)
                }
            }


            // ───────────────────────────────────────────────────────
            // STEP 5 — RETURN THE FINISHED REPORT
            // Hand back a HazardReport carrying the real id SQLite assigned,
            // plus the values we just stored. This is the .use block's result,
            // so it's what 'return' (STEP 2) gives back to the caller.
            // ───────────────────────────────────────────────────────
            HazardReport(
                id = id,
                type = type,
                description = description,
                lat = lat,
                lng = lng,
                image = image
            )
        }
    }


    /**
     * Returns all stored reports ordered by ascending ID.
     * Returns an empty list when no reports exist.
     */
    override fun getAllReports(): List<HazardReport> {

        // Open a connection (auto-closed by .use).
        return DriverManager.getConnection(databaseUrl).use { connection ->

            // A plain statement is fine here — there are no user values to bind.
            connection.createStatement().use { statement ->

                // SELECT every column of every row. ORDER BY id guarantees
                // they come back in insertion order, not a random order.
                statement.executeQuery("SELECT * FROM $tableName ORDER BY id").use { result ->

                    // buildList { } collects items into a List as we go.
                    buildList {

                        // Walk the cursor one row at a time. .next() moves to the
                        // next row and returns false when there are none left.
                        while (result.next()) {

                            // Convert the current row into a HazardReport and add it.
                            add(result.toHazardReport())
                        }
                    }
                }
            }
        }
    }


    /**
     * Returns the report with [reportId], or null if it does not exist.
     */
    override fun getReport(reportId: Long): HazardReport? {

        return DriverManager.getConnection(databaseUrl).use { connection ->

            // Parameterized SELECT — the id is user input, so it goes through a ?.
            connection.prepareStatement("SELECT * FROM $tableName WHERE id = ?").use { statement ->

                // Bind the id into placeholder 1.
                statement.setLong(1, reportId)

                statement.executeQuery().use { result ->

                    // If there's a matching row, map it; otherwise return null.
                    // The '?' return type forces callers to handle the "not found" case.
                    if (result.next()) {
                        result.toHazardReport()
                    } else {
                        null
                    }
                }
            }
        }
    }


    /**
     * Converts the current database row into the shared report model.
     * The result cursor must already point to a row.
     *
     * This is an EXTENSION function on ResultSet (note "ResultSet.toHazardReport").
     * Writing it once here means both getAllReports and getReport reuse the same
     * row-to-object mapping (DRY) — one place to write it, one place to change it.
     */
    private fun ResultSet.toHazardReport(): HazardReport {
        return HazardReport(

            // Read each column out of the current row, BY NAME:
            id          = getLong("id"),
            type        = HazardType.valueOf(getString("type")),  // "POTHOLE" (String) → HazardType.POTHOLE
            description = getString("description"),
            lat         = getDouble("lat"),
            lng         = getDouble("lng"),
            image       = getString("image")
        )
    }
}