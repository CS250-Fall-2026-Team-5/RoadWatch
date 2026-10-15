package edu.sdsu.cs250.team5.road_watch

class ReportSubmissionController(
    private val repository: HazardRepository =
        InMemoryHazardRepository()
) {
    var submittedReport: HazardReport? = null
        private set

    var errorMessage: String? = null
        private set

    fun submitReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String? = null
    ): Boolean {
        return try {
            submittedReport = repository.addReport(
                type = type,
                description = description,
                lat = lat,
                lng = lng,
                image = image
            )

            errorMessage = null
            true
        } catch (error: IllegalArgumentException) {
            submittedReport = null
            errorMessage = error.message
                ?: "The report contains invalid information"
            false
        }
    }

    fun clearMessages() {
        submittedReport = null
        errorMessage = null
    }
}