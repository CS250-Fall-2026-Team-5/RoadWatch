package edu.sdsu.cs250.team5.road_watch.storage

import edu.sdsu.cs250.team5.road_watch.HazardType
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertEquals

class SqliteHazardRepositoryTest {

    @Test
    fun storedReportIsRetrievedUnchanged() {
        // Use an isolated database, leaving real reports untouched.
        val databaseFile = Files.createTempFile("roadwatch-test-", ".db")
        val databaseUrl = "jdbc:sqlite:${databaseFile.toAbsolutePath()}"

        try {
            initializeDatabase(databaseUrl)
            val writer = SqliteHazardRepository(databaseUrl)

            // Surrounding spaces must be preserved by REQ-2.
            val description = "  Deep pothole in the right lane  "

            val saved = writer.addReport(
                type = HazardType.POTHOLE,
                description = description,
                lat = 32.7757,
                lng = -117.0719,
                image = null
            )

            // A new repository reads the same file through a new connection.
            val reader = SqliteHazardRepository(databaseUrl)
            val reports = reader.getAllReports()

            assertEquals(1, reports.size)

            val retrieved = reports.single()
            assertEquals(saved.id, retrieved.id)
            assertEquals(HazardType.POTHOLE, retrieved.type)
            assertEquals(description, retrieved.description)
            assertEquals(32.7757, retrieved.lat)
            assertEquals(-117.0719, retrieved.lng)
        } finally {
            // Connections are closed by the repository's use blocks.
            Files.deleteIfExists(databaseFile)
        }
    }
}