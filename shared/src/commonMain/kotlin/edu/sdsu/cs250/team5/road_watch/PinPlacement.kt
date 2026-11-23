package edu.sdsu.cs250.team5.road_watch

import kotlin.math.abs

object PinPlacement {
    // True only when the pin was dragged out of its starting box
    fun isOutsideStartBox(offsetX: Float, offsetY: Float, halfBoxPx: Float): Boolean =
        abs(offsetX) > halfBoxPx || abs(offsetY) > halfBoxPx
}
