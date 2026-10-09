package edu.sdsu.cs250.team5.road_watch

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf

expect fun createMainViewController(content: @Composable () -> Unit): Any
class MainViewController : ViewModel() {
    val coordinates = mutableStateOf<LocationCoordinates?>(null)
    val error = mutableStateOf<String?>(null)

    fun loadLocation() {
        viewModelScope.launch {
            try {
                val result = LocationService().fetchCurrentLocation()

                if (result == null) {
                    error.value = "Could not get device location"
                } else {
                    coordinates.value = result
                }
            } catch (e: Exception) {
                error.value = e.message ?: "Location error"
            }
        }
    }
}