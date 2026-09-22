package edu.sdsu.cs250.team5.road_watch

/*
The enum class is fixed values which
give us our categories for the hazards
 */
enum class HazardType {
    POTHOLE,
    BROKEN_LIGHT,
    DEBRIS,
    FLOODING,
    OTHER
}
/*
This is the main class which allows the app to categorize information & every field is read only
val id: Long --- The identifier to each report
val type: HazardType --- The kind of hazard
val description: String --- User description of the event
val lat: Double --- The map needs this for the latitude
val lng: Double --- The map needs this for the longitude
val image: String? = nul --- A photo of the problem which is optional
 */
data class HazardReport(
    val id: Long,
    val type: HazardType,
    val description: String,
    val lat: Double,
    val lng: Double,
    val image: String? = null,
)