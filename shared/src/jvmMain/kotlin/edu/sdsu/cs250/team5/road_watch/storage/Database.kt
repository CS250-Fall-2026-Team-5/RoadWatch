package edu.sdsu.cs250.team5.road_watch.storage


import java.sql.DriverManager

/**
 * Opens or creates the database and creates the reports table if needed.
 *
 * Relative database paths use the process working directory.
 * Existing reports persist across application restarts.
 * This function creates the initial table; it does not migrate existing tables.
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