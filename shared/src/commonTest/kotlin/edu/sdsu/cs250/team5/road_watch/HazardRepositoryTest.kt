package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class HazardRepositoryTest {

    @Test
    fun submittedReportIsStoredAndRetrievable() {
        val repo = InMemoryHazardRepository()

        val saved = repo.addReport(
            type = HazardType.POTHOLE,
            description = "Deep pothole in the right lane",
            lat = 32.7757,
            lng = -117.0719
        )

        assertTrue(saved.id > 0)
        assertEquals(1, repo.getAllReports().size)

        val fetched = repo.getReport(saved.id)

        assertNotNull(fetched)
        assertEquals(HazardType.POTHOLE, fetched.type)
        assertEquals(
            "Deep pothole in the right lane",
            fetched.description
        )
        assertEquals(32.7757, fetched.lat)
        assertEquals(-117.0719, fetched.lng)
    }

    @Test
    fun reportsAreReturnedInSubmissionOrder() {
        val repo = InMemoryHazardRepository()

        repo.addReport(
            type = HazardType.POTHOLE,
            description = "first report",
            lat = 32.0,
            lng = -117.0
        )

        repo.addReport(
            type = HazardType.BROKEN_LIGHT,
            description = "second report",
            lat = 32.1,
            lng = -117.1
        )

        val allReports = repo.getAllReports()

        assertEquals(2, allReports.size)
        assertEquals("first report", allReports[0].description)
        assertEquals("second report", allReports[1].description)
    }

    @Test
    fun blankDescriptionIsRejected() {
        val repo = InMemoryHazardRepository()

        assertFailsWith<IllegalArgumentException> {
            repo.addReport(
                type = HazardType.OTHER,
                description = "   ",
                lat = 32.0,
                lng = -117.0
            )
        }

        assertEquals(0, repo.getAllReports().size)
    }

    @Test
    fun outOfRangeLatitudeIsRejected() {
        val repo = InMemoryHazardRepository()

        assertFailsWith<IllegalArgumentException> {
            repo.addReport(
                type = HazardType.POTHOLE,
                description = "Invalid latitude",
                lat = 200.0,
                lng = -117.0
            )
        }

        assertEquals(0, repo.getAllReports().size)
    }

    @Test
    fun outOfRangeLongitudeIsRejected() {
        val repo = InMemoryHazardRepository()

        assertFailsWith<IllegalArgumentException> {
            repo.addReport(
                type = HazardType.POTHOLE,
                description = "Invalid longitude",
                lat = 32.0,
                lng = 200.0
            )
        }

        assertEquals(0, repo.getAllReports().size)
    }

    @Test
    fun descriptionOver500CharactersIsRejected() {
        val repo = InMemoryHazardRepository()
        val longDescription = "a".repeat(501)

        assertFailsWith<IllegalArgumentException> {
            repo.addReport(
                type = HazardType.POTHOLE,
                description = longDescription,
                lat = 32.0,
                lng = -117.0
            )
        }

        assertEquals(0, repo.getAllReports().size)
    }

    @Test
    fun descriptionWithExactly500CharactersIsAccepted() {
        val repo = InMemoryHazardRepository()

        val saved = repo.addReport(
            type = HazardType.OTHER,
            description = "a".repeat(500),
            lat = 32.0,
            lng = -117.0
        )

        assertEquals(500, saved.description.length)
    }

    @Test
    fun unsupportedImageTypeIsRejected() {
        val repo = InMemoryHazardRepository()

        assertFailsWith<IllegalArgumentException> {
            repo.addReport(
                type = HazardType.POTHOLE,
                description = "Hazard with unsupported image",
                lat = 32.0,
                lng = -117.0,
                image = "hazard.txt"
            )
        }

        assertEquals(0, repo.getAllReports().size)
    }

    @Test
    fun supportedImageTypeIsStored() {
        val repo = InMemoryHazardRepository()

        val saved = repo.addReport(
            type = HazardType.POTHOLE,
            description = "Hazard with image",
            lat = 32.0,
            lng = -117.0,
            image = "hazard.jpg"
        )

        assertEquals("hazard.jpg", saved.image)
    }

    @Test
    fun uppercaseImageExtensionIsAccepted() {
        val repo = InMemoryHazardRepository()

        val saved = repo.addReport(
            type = HazardType.POTHOLE,
            description = "Image test",
            lat = 32.0,
            lng = -117.0,
            image = "hazard.JPG"
        )

        assertEquals("hazard.JPG", saved.image)
    }

    @Test
    fun unknownIdReturnsNull() {
        val repo = InMemoryHazardRepository()

        assertNull(repo.getReport(999L))
    }
}
