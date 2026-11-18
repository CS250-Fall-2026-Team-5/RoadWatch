package edu.sdsu.cs250.team5.road_watch

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PinPlacementTest {
    private val half = 50f

    @Test
    fun yf04_pinPutBackInBoxDoesNotOpenForm() {
        assertFalse(PinPlacement.isOutsideStartBox(10f, -10f, half))
    }

    @Test
    fun yf04_pinOnBoxEdgeDoesNotOpenForm() {
        assertFalse(PinPlacement.isOutsideStartBox(50f, 0f, half))
    }

    @Test
    fun yf04_pinDraggedOntoMapOpensForm() {
        assertTrue(PinPlacement.isOutsideStartBox(0f, -300f, half))
    }
}