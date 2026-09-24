package edu.sdsu.cs250.team5.road_watch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun platformMap(
    latitude: Double,
    longitude: Double,
    modifier: Modifier
)