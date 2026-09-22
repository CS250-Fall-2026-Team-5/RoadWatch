package edu.sdsu.cs250.team5.road_watch
import kotlin.math.log


interface HazardRepository {
    /*
    Stores a new report. Returns the finished HazardReport with its new id.
    image has default set to null if users skip it
     */
    fun addReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String? = null
    ): HazardReport

    // Return every stored report so the map can draw them all
    fun getAllReports(): List<HazardReport>

    // Look up one report by its id, if no report with that id exits returns null
    fun getReport(reportId: Long): HazardReport?

}
/*
The actual backend for the skeleton this class implements the interface above
The reports exists in memory since we do not have a database yet
 */
class InMemoryHazardRepository : HazardRepository {

    // Private list which holds the reports
    private val reports = mutableListOf<HazardReport>()

    // Updates the id per new request using var instead of val
    private var nextId = 1L

    // Override is used because we are implementing an interface function
    override fun addReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String?
    ): HazardReport {
        // This is the backend refusing bad data
        require(description.isNotBlank()) { "Description must not be blank" }
        require(lat in -90.0..90.0) { "Latitude out of range: $lat" }
        require(lng in -180.0..180.0) { "Longitude out of range: $lng" }

        /*
        Building the report nextId++ increments the id then .trim() gets rid of spare spaces before storing
         */
        val report = HazardReport(
            id = nextId++,
            type = type,
            description = description.trim(),
            lat = lat,
            lng = lng,
            image = image,
        )
        reports.add(report) // stores it into the list
        return report
    }

    // Returns a safe read only copy so anything calling cant change the list
    override fun getAllReports(): List<HazardReport> = reports.toList()

    // Goes through the list and returns the first match or null if none
    override fun getReport(reportId: Long): HazardReport? = reports.firstOrNull { it.id == reportId }
}
