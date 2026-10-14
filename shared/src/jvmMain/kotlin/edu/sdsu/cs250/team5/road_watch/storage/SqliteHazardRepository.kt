package edu.sdsu.cs250.team5.road_watch.storage

import edu.sdsu.cs250.team5.road_watch.HazardReport
import edu.sdsu.cs250.team5.road_watch.HazardRepository
import edu.sdsu.cs250.team5.road_watch.HazardType
import edu.sdsu.cs250.team5.road_watch.ReportValidator
import java.sql.DriverManager
import java.sql.ResultSet

/**
 * Stores and retrieves hazard reports using SQLite.
 */
class SqliteHazardRepository(
    private val databaseUrl: String = "jdbc:sqlite:roadwatch.db"
) : HazardRepository {

    private val tableName = "reports"

    override fun addReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String?
    ): HazardReport {

        val validationError = ReportValidator.validate(
            description = description,
            lat = lat,
            lng = lng,
            image = image
        )

        require(validationError == null) {
            validationError ?: "Invalid hazard report"
        }

        return DriverManager.getConnection(databaseUrl).use { connection ->
            connection.prepareStatement(
                """
                INSERT INTO $tableName (type, description, lat, lng, image)
                VALUES (?, ?, ?, ?, ?)
                """.trimIndent()
            ).use { statement ->
                statement.setString(1, type.name)
                statement.setString(2, description)
                statement.setDouble(3, lat)
                statement.setDouble(4, lng)
                statement.setString(5, image)
                statement.executeUpdate()
            }

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

    override fun getAllReports(): List<HazardReport> {
        return DriverManager.getConnection(databaseUrl).use { connection ->
            connection.createStatement().use { statement ->
                statement.executeQuery(
                    "SELECT * FROM $tableName ORDER BY id"
                ).use { result ->
                    buildList {
                        while (result.next()) {
                            add(result.toHazardReport())
                        }
                    }
                }
            }
        }
    }

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
