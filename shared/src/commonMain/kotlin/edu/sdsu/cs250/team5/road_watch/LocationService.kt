package edu.sdsu.cs250.team5.road_watch

import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.GeolocatorResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

class LocationService {
    private val geolocator: Geolocator? = provideGeolocator()

    suspend fun fetchCurrentLocation(): LocationCoordinates? {
        val locator = geolocator ?: return null

        return try {
            locator.startTracking()

            val location = locator.locationUpdates.first()
            val coordinates = location.coordinates

            LocationCoordinates(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude
            )
        } catch (e: Exception) {
            println("Location error: ${e.message}")
            null
        } finally {
            locator.stopTracking()
        }
    }
}