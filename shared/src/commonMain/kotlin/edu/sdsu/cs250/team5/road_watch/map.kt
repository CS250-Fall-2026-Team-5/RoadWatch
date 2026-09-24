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

    LaunchedEffect(Unit) {
        controller.loadLocation()
    }

    when {
        controller.error.value != null -> {
            Text("Error: ${controller.error.value}")
        }

        controller.coordinates.value == null -> {
            CircularProgressIndicator()
        }

        else -> {
            platformMap(
                coordinates = controller.coordinates.value,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}