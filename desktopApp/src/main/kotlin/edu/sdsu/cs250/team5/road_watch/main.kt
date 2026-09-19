package edu.sdsu.cs250.team5.road_watch

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "RoadWatch",
    ) {
        App()
    }
}