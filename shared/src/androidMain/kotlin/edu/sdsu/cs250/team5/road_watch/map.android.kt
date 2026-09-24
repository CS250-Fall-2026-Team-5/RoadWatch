package edu.sdsu.cs250.team5.road_watch

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
actual fun platformMap(
    coordinates: LocationCoordinates?,
    modifier: Modifier
) {
    if (coordinates == null) {
        CircularProgressIndicator()
        return
    }

    val latLng = coordinates?.let {
        LatLng(it.latitude, it.longitude)
    } ?: LatLng(0.0, 0.0)
    val markerState = remember(latLng) {
        MarkerState(position = latLng)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 15f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = markerState,
            title = "Current location"
        )
    }
}