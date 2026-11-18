package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class CoordinateLimitsTest {

    private val description = "Pothole near entrance"
    private val longitude = -117.0719

    @Test
    fun yf02_latitudeJustBelowLimitIsAccepted() {
        assertNull(ReportValidator.validate(description, 89.9999, longitude, null))
    }

    @Test
    fun yf02_latitudeExactlyAtLimitIsAccepted() {
        assertNull(ReportValidator.validate(description, 90.0, longitude, null))
    }

    @Test
    fun yf02_latitudeJustAboveLimitIsRejected() {
        assertEquals(
            "Latitude must be between -90 and 90",
            ReportValidator.validate(description, 90.0001, longitude, null)
        )
    }
}
