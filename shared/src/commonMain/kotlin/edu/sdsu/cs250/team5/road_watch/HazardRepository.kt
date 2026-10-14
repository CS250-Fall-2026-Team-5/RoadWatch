package edu.sdsu.cs250.team5.road_watch

interface HazardRepository {

    fun addReport(
        type: HazardType,
        description: String,
        lat: Double,
        lng: Double,
        image: String? = null
    ): HazardReport

    fun getAllReports(): List<HazardReport>

    fun getReport(reportId: Long): HazardReport?
}

class InMemoryHazardRepository : HazardRepository {

    private val reports = mutableListOf<HazardReport>()

    private var nextId = 1L

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

        val report = HazardReport(
            id = nextId++,
            type = type,
            description = description.trim(),
            lat = lat,
            lng = lng,
            image = image?.trim()
        )

        reports.add(report)

        return report
    }

    override fun getAllReports(): List<HazardReport> {
        return reports.toList()
    }

    override fun getReport(reportId: Long): HazardReport? {
        return reports.firstOrNull { it.id == reportId }
    }
}
