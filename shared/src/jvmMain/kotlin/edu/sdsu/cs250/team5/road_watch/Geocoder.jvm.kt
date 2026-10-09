package edu.sdsu.cs250.team5.road_watch

import dev.jordond.compass.Coordinates
import dev.jordond.compass.geocoder.Geocoder

suspend fun getCoordinatesFromAddressJvm(addressString: String): List<Coordinates>? {
    val geocoder = Geocoder(apiKey = BuildKonfig.DESKTOP_API_KEY)

    return try {
        val result = geocoder.forward(addressString)

        println("Geocoding result: $result")

        result.getOrNull()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
