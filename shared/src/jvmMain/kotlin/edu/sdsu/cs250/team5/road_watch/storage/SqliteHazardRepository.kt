package edu.sdsu.cs250.team5.road_watch.storage

import edu.sdsu.cs250.team5.road_watch.HazardReport
import edu.sdsu.cs250.team5.road_watch.HazardRepository
import edu.sdsu.cs250.team5.road_watch.HazardType
import java.sql.DriverManager
import java.sql.ResultSet

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
    private val databaseUrl: String = "jdbc:sqlite:roadwatch.db"
) : HazardRepository {

    // Fixed table name used by the repository's SQL statements.
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
        // Reject invalid input before creating a database row.
        require(description.isNotBlank()) {
            "Description must not be blank"
        }
        require(lat in -90.0..90.0) {
            "Latitude out of range: $lat"
        }
        require(lng in -180.0..180.0) {
            "Longitude out of range: $lng"
        }

        // use closes the connection even if an operation fails.
        return DriverManager.getConnection(databaseUrl).use { connection ->
            // The table name is fixed internally; user values use placeholders.
            connection.prepareStatement(
                """
                INSERT INTO $tableName (type, description, lat, lng, image)
                VALUES (?, ?, ?, ?, ?)
                """.trimIndent()
            ).use { statement ->
                // Store the enum name so it can be reconstructed later.
                statement.setString(1, type.name)
                statement.setString(2, description)
                statement.setDouble(3, lat)
                statement.setDouble(4, lng)

                // A null image reference becomes SQL NULL.
                statement.setString(5, image)
                statement.executeUpdate()
            }

            // Read the generated ID on the same connection as the insert.
            val id = connection.createStatement().use { statement ->
                statement.executeQuery(
                    "SELECT last_insert_rowid()"
                ).use { result ->
                    check(result.next()) {
                        "Could not read the new report ID"
                    }
                    result.getLong(1)
                }
            }

            // Return the assigned ID and the values written to SQLite.
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
        return DriverManager.getConnection(databaseUrl).use { connection ->
            connection.createStatement().use { statement ->
                statement.executeQuery(
                    "SELECT * FROM $tableName ORDER BY id"
                ).use { result ->
                    buildList {
                        // Advance through the rows and convert each to a report.
                        while (result.next()) {
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
            connection.prepareStatement(
                "SELECT * FROM $tableName WHERE id = ?"
            ).use { statement ->
                statement.setLong(1, reportId)

                statement.executeQuery().use { result ->
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
     */
    private fun ResultSet.toHazardReport(): HazardReport {
        return HazardReport(
            id = getLong("id"),
            type = HazardType.valueOf(getString("type")),
            description = getString("description"),
            lat = getDouble("lat"),
            lng = getDouble("lng"),
            image = getString("image")
        )
    }
}