package edu.sdsu.cs250.team5.road_watch

import dev.jordond.compass.geolocation.Geolocator

//Calls Geolocator class from required device.
expect fun provideGeolocator(): Geolocator?