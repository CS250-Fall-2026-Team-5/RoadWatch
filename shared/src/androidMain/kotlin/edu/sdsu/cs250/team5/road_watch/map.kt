package edu.sdsu.cs250.team5.road_watch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import edu.sdsu.cs250.team5.road_watch.LocationService

@Composable
actual fun PlatformMap(
    latitude: Double,
    longitude: Double,
    modifier: Modifier
) {
    val location = LocationService()
    val position = LatLng(location.fetchCurrentLocation("lat"),location.fetchCurrentLocation("lng") )

    val cameraPositionState = rememberCameraPositionState {
        this.position = CameraPosition.fromLatLngZoom(position, 15f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = position),
            title = "Current location"
        )
    }
}