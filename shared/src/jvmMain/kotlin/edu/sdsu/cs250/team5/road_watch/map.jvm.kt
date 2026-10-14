package edu.sdsu.cs250.team5.road_watch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.swmansion.kmpmaps.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
actual fun platformMap(
    coordinates: LocationCoordinates?,
    modifier: Modifier
) {
    MapConfiguration.initialize(
        googleMapsApiKey = BuildKonfig.DESKTOP_API_KEY
    )

    var mapCoordinates by remember {
        mutableStateOf(coordinates)
    }

    LaunchedEffect(coordinates) {
        mapCoordinates = coordinates ?: withContext(Dispatchers.IO) {
            getCoordinatesFromAddressJvm(
                "6075 Aztec Circle Dr, San Diego, CA 92182"
            )
                ?.firstOrNull()
                ?.let {
                    LocationCoordinates(
                        latitude = it.latitude,
                        longitude = it.longitude
                    )
                }
        }
    }

    val location = mapCoordinates

    if (location != null) {
        val mapLocation = Coordinates(
            latitude = location.latitude,
            longitude = location.longitude
        )

        Map(
            modifier = modifier,
            cameraPosition = CameraPosition(
                coordinates = mapLocation,
                zoom = 15f
            ),
            markers = listOf(
                Marker(
                    coordinates = mapLocation,
                    title = "San Diego State University"
                )
            )
        )
    }
}