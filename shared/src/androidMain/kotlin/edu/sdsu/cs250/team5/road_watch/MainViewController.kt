package edu.sdsu.cs250.team5.road_watch

import androidx.compose.runtime.Composable

actual fun createMainViewController(content: @Composable () -> Unit): Any {
    return content
}