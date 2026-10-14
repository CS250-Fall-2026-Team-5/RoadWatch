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
//Passing in map request from app.kt and using coordinates from 
    LaunchedEffect(coordinates, mapSearchRequest) {
        if(mapSearchRequest.isNotBlank()) {
            val searchedLocation = withContext(Dispatchers.IO) {
                getCoordinatesFromAddressJvm(mapSearchRequest)
                ?.firstOrNull()
                ?.let{
                    LocationCoordinates(
                        latitude = it.latitude,
                        longitude = it.longitude
                        )
                }
            }
// update map to searched location from our map
    if (searchedLocation != null) {
        mapCoordinates = searchedLocation
    }
    else {
        println("Creating map with map Search Request")
    }
        }else {
            // start map at Aztec circle
            mapCoordinates = coordinates ?: withContext(Dispatchers.IO){
                getCoordinatesFromAddressJvm(
                    "6075 Aztec Circle Dr, San Diego, CA 92182"
                    )
                ?.firstOrNull()
                ?.let{
                    LocationCoordinates(
                        latitude = it.latitude,
                        longitude = it.longitude
                        )
                }
            } ?:LocationCoordinates(
                latitude = 32.7757,
                longitude = -117.0719
                )
        }
    }
    //Gets the current map location
    val location = mapCoordinates
    //will only display if our coodinates are there

    if (location != null) {
        val mapLocation = Coordinates(
            latitude = location.latitude,
            longitude = location.longitude
            )
        //Remakes the map when our location is updated
        key(mapLocation){
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
                ),
            onMapLoaded = {
                println("Google maps finshed loading")
            }
            

        
                
        )
    }
}
}
