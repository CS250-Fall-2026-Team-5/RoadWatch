package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class HazardRepositoryTest {

    // REQ-1 + REQ-2: submit a report, then read it back with its fields intact.
    @Test
    fun submittedReportIsStoredAndRetrievable() {
        val repo = InMemoryHazardRepository()

        val saved = repo.addReport(
            type = HazardType.POTHOLE,
            description = "Deep pothole in the right lane",
            lat = 32.7757,
            lng = -117.0719
        )

        assertTrue(saved.id > 0, "a stored report should receive a positive id")
        assertEquals(1, repo.getAllReports().size)

        val fetched = repo.getReport(saved.id)
        assertNotNull(fetched)
        assertEquals(HazardType.POTHOLE, fetched.type)
        assertEquals("Deep pothole in the right lane", fetched.description)
        assertEquals(32.7757, fetched.lat)
        assertEquals(-117.0719, fetched.lng)
    }

    // REQ-2 / REQ-3: reports come back in the order they were submitted.
    @Test
    fun reportsAreReturnedInSubmissionOrder() {
        val repo = InMemoryHazardRepository()
        repo.addReport(HazardType.POTHOLE, "first report", 32.0, -117.0)
        repo.addReport(HazardType.BROKEN_LIGHT, "second report", 32.1, -117.1)

        val all = repo.getAllReports()
        assertEquals(2, all.size)
        assertEquals("first report", all[0].description)
        assertEquals("second report", all[1].description)
    }

    // Verification: a blank description is rejected and nothing is stored.
    @Test
    fun blankDescriptionIsRejected() {
        val repo = InMemoryHazardRepository()
        assertFailsWith<IllegalArgumentException> {
            repo.addReport(HazardType.OTHER, "   ", 32.0, -117.0)
        }
        assertEquals(0, repo.getAllReports().size)
    }

    // Verification: an out-of-range coordinate is rejected.
    @Test
    fun outOfRangeCoordinatesAreRejected() {
        val repo = InMemoryHazardRepository()
        assertFailsWith<IllegalArgumentException> {
            repo.addReport(HazardType.POTHOLE, "bad latitude", 200.0, -117.0)
        }
    }

    // Safety: an id that was never stored returns null instead of crashing.
    @Test
    fun unknownIdReturnsNull() {
        val repo = InMemoryHazardRepository()
        assertNull(repo.getReport(999L))
    }
}