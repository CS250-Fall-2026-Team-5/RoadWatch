package edu.sdsu.cs250.team5.road_watch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
expect fun platformMap(
    coordinates: LocationCoordinates?,
    modifier: Modifier = Modifier
)
@Composable
fun LocationScreen() {
    val controller = remember { MainViewController() }
    val currentCoordinates = controller.coordinates.value
    val currentError = controller.error.value
    val platform = remember { getPlatform() }
    val isJvm = platform.name.startsWith("Java ")

    LaunchedEffect(Unit) {
        if (!isJvm) {
            controller.loadLocation()
        }
    }
    if (isJvm) {
        platformMap(
            coordinates = null,
            modifier = Modifier.fillMaxSize()
        )
    }
    else {
        when {
            currentError != null -> {
                Text("Error: $currentError")
            }

            currentCoordinates == null -> {
                CircularProgressIndicator()
            }

            else -> {
                platformMap(
                    coordinates = currentCoordinates,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}