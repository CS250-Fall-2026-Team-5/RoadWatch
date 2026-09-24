package edu.sdsu.cs250.team5.road_watch

import dev.jordond.compass.geolocation.Geolocator
import dev.jordond.compass.geolocation.mobile

actual fun provideGeolocator(): Geolocator? {
    return Geolocator.mobile()
}