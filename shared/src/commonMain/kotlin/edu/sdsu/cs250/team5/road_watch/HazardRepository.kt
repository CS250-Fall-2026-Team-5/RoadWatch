package edu.sdsu.cs250.team5.road_watch

// Maximum number of characters allowed in a hazard description.
private const val MAX_DESCRIPTION_LENGTH = 500

// Image extensions accepted by the application.
private val SUPPORTED_IMAGE_EXTENSIONS =
    setOf(".jpg", ".jpeg", ".png")

interface HazardRepository {

    /*
     * Stores a new hazard report and returns the saved report.
     * The image is optional.
     */
    fun addReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String? = null
    ): HazardReport

    // Returns all stored hazard reports.
    fun getAllReports(): List<HazardReport>

    // Finds a report by ID, or returns null if it does not exist.
    fun getReport(reportId: Long): HazardReport?
}

/*
 * Temporary in-memory backend for the walking skeleton.
 * Reports are stored in a list until a database is connected.
 */
class InMemoryHazardRepository : HazardRepository {

    // Stores all valid hazard reports.
    private val reports = mutableListOf<HazardReport>()

    // Generates a unique ID for each new report.
    private var nextId = 1L

    override fun addReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String?
    ): HazardReport {

        // Rejects reports with an empty or blank description.
        require(description.isNotBlank()) {
            "Description must not be blank"
        }

        // Rejects descriptions longer than the REQ-8 limit.
        require(description.length <= MAX_DESCRIPTION_LENGTH) {
            "Description must not exceed 500 characters"
        }

        // Validates the latitude coordinate.
        require(lat in -90.0..90.0) {
            "Latitude out of range: $lat"
        }

        // Validates the longitude coordinate.
        require(lng in -180.0..180.0) {
            "Longitude out of range: $lng"
        }

        // If an image is provided, verify that its file type is supported.
        if (image != null) {
            val lowerCaseImage = image.lowercase()

            require(
                SUPPORTED_IMAGE_EXTENSIONS.any {
                    lowerCaseImage.endsWith(it)
                }
            ) {
                "Unsupported image file type"
            }
        }

        // Creates a report after all validation checks pass.
        val report = HazardReport(
            id = nextId++,
            type = type,
            description = description.trim(),
            lat = lat,
            lng = lng,
            image = image
        )

        // Stores the valid report and returns it to the caller.
        reports.add(report)
        return report
    }

    // Returns a read-only copy of all reports.
    override fun getAllReports(): List<HazardReport> =
        reports.toList()

    // Returns the report matching the requested ID.
    override fun getReport(reportId: Long): HazardReport? =
        reports.firstOrNull { it.id == reportId }
}
