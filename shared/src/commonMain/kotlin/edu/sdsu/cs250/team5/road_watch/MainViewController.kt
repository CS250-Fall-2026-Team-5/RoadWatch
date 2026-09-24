package edu.sdsu.cs250.team5.road_watch

import androidx.compose.runtime.Composable
import edu.sdsu.cs250.team5.road_watch.LocationCoordinates
import edu.sdsu.cs250.team5.road_watch.LocationService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

expect fun createMainViewController(content: @Composable () -> Unit): Any
fun loadLocation() {
    val locationService = LocationService()
    viewModelScope.launch {
        val coordinates = locationService.fetchCurrentLocation()

        coordinates?.let {
            println("Lat: ${it.latitude}, Lng: ${it.longitude}")
        }
    }
}