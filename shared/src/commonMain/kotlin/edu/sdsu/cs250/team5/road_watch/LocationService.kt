package edu.sdsu.cs250.team5.road_watch

import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.GeolocatorResult

class LocationService {
    private val geolocator: Geolocator? = provideGeolocator()

    suspend fun fetchCurrentLocation(): LocationCoordinates? {
        val locator = geolocator ?: return null

        return when (val result = locator.current()) {
            is GeolocatorResult.Success -> {
                val coordinates = result.data.coordinates

                LocationCoordinates(
                    latitude = coordinates.latitude,
                    longitude = coordinates.longitude
                )
            }

            is GeolocatorResult.Error -> {
                println("Failed to fetch location: $result")
                null
            }
        }
    }
}