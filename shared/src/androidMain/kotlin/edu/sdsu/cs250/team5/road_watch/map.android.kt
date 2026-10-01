package edu.sdsu.cs250.team5.road_watch

import android.os.Bundle
import android.content.pm.PackageManager
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import edu.sdsu.cs250.team5.road_watch.BuildKonfig

@Composable
actual fun platformMap(
    coordinates: LocationCoordinates?,
    modifier: Modifier
) {
    if (coordinates == null) {
        CircularProgressIndicator()
        return
    }
    val context = LocalContext.current
    var isMapsInitialized by remember { mutableStateOf(false) }
    LaunchedEffect(context) {
        com.google.android.gms.maps.MapsInitializer.initialize(context, com.google.android.gms.maps.MapsInitializer.Renderer.LATEST) {
            isMapsInitialized = true
        }
    }
    if (!isMapsInitialized) {
        CircularProgressIndicator()
    }
    else {
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
}