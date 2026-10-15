package edu.sdsu.cs250.team5.road_watch
import com.swmansion.kmpmaps.core.getCoordinatesFromAddressJvm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
//test to prove address look up is functional in our application, searches a given address
//checks if said address is  avalid georaddress falls in valid latitude and longitude coordinates
class MapSearchTest {
  fun knownAddressReturnValidCoordinates() = runBlocking {
    val address = "1600 Amphitheatre Parkwat, Mountain View, CA 94043"
    val results = withContext(Dispatchers.IO){
      getCoordinatesFromAddressJvm(address)
    }
    valo location = assertNotNull(
      results?.firstOrNull(),
      "expect geocoder to return coordinates for a known address"
      )
    assertTrue(
      location.latitude in -90.0..90.0
      )
      
      
