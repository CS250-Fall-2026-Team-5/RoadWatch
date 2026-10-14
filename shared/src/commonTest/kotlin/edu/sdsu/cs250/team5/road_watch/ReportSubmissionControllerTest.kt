package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ReportSubmissionControllerTest {

    @Test
    fun validReportIsSubmittedSuccessfully() {
        val controller = ReportSubmissionController()

        val result = controller.submitReport(
            type = HazardType.POTHOLE,
            description = "Pothole near the entrance",
            lat = 32.7757,
            lng = -117.0719
        )

        assertTrue(result)
        assertNotNull(controller.submittedReport)
        assertNull(controller.errorMessage)
    }

    @Test
    fun blankDescriptionShowsAnError() {
        val controller = ReportSubmissionController()

        val result = controller.submitReport(
            type = HazardType.OTHER,
            description = "   ",
            lat = 32.0,
            lng = -117.0
        )

        assertFalse(result)
        assertNull(controller.submittedReport)

        assertEquals(
            "Description must not be blank",
            controller.errorMessage
        )
    }

    @Test
    fun invalidCoordinatesShowAnError() {
        val controller = ReportSubmissionController()

        val result = controller.submitReport(
            type = HazardType.POTHOLE,
            description = "Invalid location",
            lat = 200.0,
            lng = -117.0
        )

        assertFalse(result)
        assertNull(controller.submittedReport)

        assertEquals(
            "Latitude must be between -90 and 90",
            controller.errorMessage
        )
    }

    @Test
    fun clearMessagesRemovesSuccessAndErrorMessages() {
        val controller = ReportSubmissionController()

        controller.submitReport(
            type = HazardType.POTHOLE,
            description = "Test report",
            lat = 32.0,
            lng = -117.0
        )

        controller.clearMessages()

        assertNull(controller.submittedReport)
        assertNull(controller.errorMessage)
    }
}
