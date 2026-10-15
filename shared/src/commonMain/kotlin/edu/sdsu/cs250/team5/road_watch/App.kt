package edu.sdsu.cs250.team5.road_watch

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import  androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import androidx.compose.material3.TextField
import androidx.compose.foundation.layout.size
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.geometry.Offset
import roadwatch.shared.generated.resources.Res
import roadwatch.shared.generated.resources.compose_multiplatform
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement

internal var mapSearchRequest by mutableStateOf("")
@Composable
@Preview
fun App() {
    MaterialTheme{
        var showLogin by remember {
            mutableStateOf(false)
        }
        var showReportBox by remember {
            mutableStateOf(false)
        }
        var searchText by remember {
            mutableStateOf("")
        }
        var isLoggedIn by remember {
            mutableStateOf(false)
        }
        var hazardDescription by remember {
            mutableStateOf("")
        }

        // (REQ-1): the backend store, remembered so reports survive recomposition
        val repo = remember {
            InMemoryHazardRepository()
        }

        //  (REQ-1): visible count of stored reports, so we can prove it saved
        var reportCount by remember {
            mutableStateOf(0)
        }

        //Creating basic box background main screen hud
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LocationScreen()
            //Creating barebones search box for searching through google maps
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        top= 20.dp,
                        end = 120.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                TextField(
                    //value of search box changes to user prompt
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    placeholder = {
                        Text("Search maps")
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                //
                Button(
                    onClick = {
                        //does a search in our textfield if not blank
                        if (searchText.isNotBlank()){
                            mapSearchRequest = searchText.trim()
                        }
                    }
                ){
                    Text("Search")
                }
            }

            //adding login variable and description variable
            if (showLogin){
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 80.dp, end = 18.dp)
                        .size(width = 250.dp, height = 280.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                        .padding(18.dp)
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Login:",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Button(
                            onClick = {
                                isLoggedIn = true
                                showLogin = false
                            },
                            modifier = Modifier.padding(top = 30.dp)
                        ) {
                            Text("Log In")
                        }
                    }
                }
            }
            //Enables hazard description box if user is logged in
            if(showReportBox && isLoggedIn){
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 10.dp, bottom = 20.dp)
                        .size(width = 300.dp, height = 300.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant
                        )
                        .padding(20.dp)
                ){
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ){
                        Text(
                            text = "Hazard Report",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "Describe the hazard as well as possible:",
                            modifier = Modifier.padding(top = 16.dp)
                        )
                        TextField(
                            value = hazardDescription,
                            onValueChange = {
                                hazardDescription = it
                            },
                            placeholder = {
                                Text("Enter your description..")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        )
                        // (REQ-1): the Submit Report button now STORES via the backend
                        Button(
                            onClick = {
                                try {
                                    // call the backend: validates then stores the hazard
                                    repo.addReport(
                                        type = HazardType.OTHER, // hardcoded other for right now because the front end does not provide ui for the hazard types
                                        description = hazardDescription,
                                        lat = 32.7757,     // default location — pin isn't geo-mapped yet
                                        lng = -117.0719
                                    )
                                    reportCount = repo.getAllReports().size  // refresh the visible count
                                    showReportBox = false
                                    hazardDescription = ""
                                } catch (e: IllegalArgumentException) {
                                    // validation failed (blank description / bad coords) — keep box open
                                }
                            },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .align(Alignment.End)
                        ){
                            Text("Submit Report")
                        }
                    }
                }
            }
            //Implementing login screen
            Button(
                onClick = {
                    showLogin = !showLogin
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(40.dp)
            ){
                Text("Login")
            }

            //Sets current position of pin on home page
            var pinOffset by remember {
                mutableStateOf(Offset.Zero)
            }
            //Implementing box to keep pin inside box itself is in background
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
                    .size(100.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ){
                //Implementing skeleton pin and logic
                Text(
                    text = "📍",
                    fontSize = 32.sp,
                    modifier = Modifier
                        .offset {
                            IntOffset(
                                pinOffset.x.roundToInt(),
                                pinOffset.y.roundToInt()
                            )
                        }
                        //Implement dragging gestures from our input device
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDrag = { change, dragAmount ->
                                    pinOffset += dragAmount
                                },
                                onDragEnd = {
                                    if(isLoggedIn) {
                                        showReportBox = true
                                    }
                                }
                            )
                        }
                )
            }

            // (REQ-1): shows how many hazards have been stored (proof it works)
            Text(
                text = "Hazards reported: $reportCount",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 60.dp)
            )
        }
    }
}