package edu.sdsu.cs250.team5.road_watch

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cocoapods.GoogleMaps.GMSMapView
import cocoapods.GoogleMaps.GMSCameraPosition
import cocoapods.GoogleMaps.GMSMarker
import androidx.compose.ui.viewinterop.UIKitView
import platform.CoreLocation.CLLocationCoordinate2DMake
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun platformMap(
    coordinates: LocationCoordinates?,
    modifier: Modifier
) {
    if (coordinates == null) {
        CircularProgressIndicator()
        return
    }
    val nativeMarker = remember {
        GMSMarker()
    }
    UIKitView(
        factory = {
            GMSMapView().apply{
                val initialpos = CLLocationCoordinate2DMake(coordinates.latitude, coordinates.longitude)
                this.camera = GMSCameraPosition.cameraWithTarget(initialpos, 15.0f)
            }
        },
        modifier = modifier,
        update = {view ->
            val longitude = coordinates.longitude
            val latitude = coordinates.latitude

            val position = CLLocationCoordinate2DMake(
                latitude = latitude,
                longitude = longitude)

            view.animateToLocation(position)

            nativeMarker.apply{
                this.position = position
                this.title = "Current Location"
                this.map = view
            }
        }
    )
}