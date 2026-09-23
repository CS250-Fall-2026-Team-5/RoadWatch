package edu.sdsu.cs250.team5.road_watch

import androidx.compose.runtime.Composable
import com.swmansion.kmpmaps.core.MapConfiguration
import edu.sdsu.cs250.team5.road_watch.BuildKonfig

actual fun createMainViewController(content: @Composable () -> Unit): Any {
    MapConfiguration.initialize(googleMapsApiKey = BuildKonfig.DESKTOP_API_KEY)
    return content
}