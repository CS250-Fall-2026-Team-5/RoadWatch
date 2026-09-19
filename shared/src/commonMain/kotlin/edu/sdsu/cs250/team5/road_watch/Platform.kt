package edu.sdsu.cs250.team5.road_watch

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform