package edu.sdsu.cs250.team5.road_watch

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import cocoapods.GoogleMaps.GMSServices

import edu.sdsu.cs250.team5.road_watch.BuildKonfig


actual fun createMainViewController(content: @Composable () -> Unit): Any {
    val iosKey = BuildKonfig.IOS_MAPS_API_KEY
    GMSServices.provideAPIKey(iosKey)

    return ComposeUIViewController {
        content()
    }
}