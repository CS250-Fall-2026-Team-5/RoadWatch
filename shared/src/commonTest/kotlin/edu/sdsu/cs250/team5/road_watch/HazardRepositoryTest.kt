package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class HazardRepositoryTest {

    // REQ-1 and REQ-2: A report is stored and can be retrieved.
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
        assertEquals("Deep pothole in the right lane", fetched.description)
        assertEquals(32.7757, fetched.lat)
        assertEquals(-117.0719, fetched.lng)
    }

    // REQ-2 and REQ-3: Reports are returned in submission order.
    @Test
    fun reportsAreReturnedInSubmissionOrder() {
        val repo = InMemoryHazardRepository()

        repo.addReport(
            HazardType.POTHOLE,
            "first report",
            32.0,
            -117.0
        )

        repo.addReport(
            HazardType.BROKEN_LIGHT,
            "second report",
            32.1,
            -117.1
        )

        val all = repo.getAllReports()

        assertEquals(2, all.size)
        assertEquals("first report", all[0].description)
        assertEquals("second report", all[1].description)
    }

    // REQ-8: Blank descriptions are rejected and not stored.
    @Test
    fun blankDescriptionIsRejected() {
        val repo = InMemoryHazardRepository()

        assertFailsWith<IllegalArgumentException> {
            repo.addReport(
                HazardType.OTHER,
                "   ",
                32.0,
                -117.0
            )
        }

        assertEquals(0, repo.getAllReports().size)
    }

    // REQ-8: Out-of-range coordinates are rejected and not stored.
    @Test
    fun outOfRangeCoordinatesAreRejected() {
        val repo = InMemoryHazardRepository()

        assertFailsWith<IllegalArgumentException> {
            repo.addReport(
                HazardType.POTHOLE,
                "bad latitude",
                200.0,
                -117.0
            )
        }

        assertEquals(0, repo.getAllReports().size)
    }

    // REQ-8: Descriptions over 500 characters are rejected.
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

    // REQ-8: Unsupported image file types are rejected.
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

    // REQ-4: Supported image file types are stored.
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

    // Safety: An unknown report ID returns null.
    @Test
    fun unknownIdReturnsNull() {
        val repo = InMemoryHazardRepository()

        assertNull(repo.getReport(999L))
    }
}
