package edu.sdsu.cs250.team5.road_watch.storage


import java.sql.DriverManager

/**
 * Opens or creates roadwatch.db and creates the reports table if needed.
 *
 * The relative database path is resolved against the process working directory
 * (the server directory when launched with the current Gradle run task).
 * Existing rows are preserved across server restarts. This function creates
 * the initial table; it does not migrate an existing table to a new schema.
 */
fun initializeDatabase(
    databaseUrl: String = "jdbc:sqlite:roadwatch.db"
) {
    // Close JDBC resources even when an operation fails.
    DriverManager.getConnection(databaseUrl).use { connection ->
        connection.createStatement().use { statement ->
            // SQLite assigns IDs. Required fields cannot be NULL.
            // Description and coordinate validation happen in the repository.
            statement.executeUpdate(
                """
                CREATE TABLE IF NOT EXISTS reports (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    type TEXT NOT NULL,
                    description TEXT NOT NULL,
                    lat REAL NOT NULL,
                    lng REAL NOT NULL,
                    image TEXT
                )
                """.trimIndent()
            )
        }
    }
}