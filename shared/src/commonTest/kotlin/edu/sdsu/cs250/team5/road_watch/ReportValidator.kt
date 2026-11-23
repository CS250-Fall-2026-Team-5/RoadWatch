package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertNotNull

class ReportValidatorTest {

    @Test
    fun rejectsUnsupportedImageFileType() {
        val result = ReportValidator.validate(
            description = "Pothole in the road",
            lat = 32.7757,
            lng = -117.0719,
            image = "hazard.php"
        )

        assertEquals(
            "Only JPG, JPEG, and PNG images are supported",
            result
        )
    }

    @Test
    fun acceptsMinimumDescription() {
        val validationError = ReportValidator.validate(
            description = "see",
            lat = 32.7757,
            lng = -117.0719,
            image = null
        )

        assertNull(validationError)
    }

    @Test
    fun rejectsLessThanMinimumDescription() {
        val validationError = ReportValidator.validate(
            description = "se",
            lat = 32.7757,
            lng = -117.0719,
            image = null
        )

        assertNotNull(validationError)
    }

    @Test
    fun testMaximumDescriptionLength() {
        val exactlyValidDescription = "e".repeat(500)
        val tooLongDescription = "e".repeat(501)

        val validResult = ReportValidator.validate(
            description = exactlyValidDescription,
            lat = 32.7757,
            lng = -117.0719,
            image = null
        )

        val invalidResult = ReportValidator.validate(
            description = tooLongDescription,
            lat = 32.7757,
            lng = -117.0719,
            image = null
        )

        assertNull(validResult)
        assertNotNull(invalidResult)
    }
}
