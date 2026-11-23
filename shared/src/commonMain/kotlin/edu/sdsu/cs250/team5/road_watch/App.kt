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
import androidx.compose.ui.window.Popup
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height

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
        var usernameText by remember {
            mutableStateOf("")
        }
        var passwordText by remember {
            mutableStateOf("")
        }
        var usernameRegText by remember {
            mutableStateOf("")
        }
        var passwordRegText by remember {
            mutableStateOf("")
        }
        var isLoggedIn by remember {
            mutableStateOf(false)
        }
        var isRegistering by remember {
            mutableStateOf(false)
        }
        var hazardDescription by remember {
            mutableStateOf("")
        }
        var reportPage by remember {
            mutableStateOf(0)
        }

        // (REQ-1): the backend store, remembered so reports survive recomposition
        val repo = remember {
            HazardRepositoryProvider.current // use whatever the platform set

        }

        //  (REQ-1): visible count of stored reports, so we can prove it saved
        var reportCount by remember {
            mutableStateOf(repo.getAllReports().size)   // start the counter from what's already saved
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
                //Implementing login screen
                Button(
                    onClick = {
                        showLogin = !showLogin
                    }
                ){
                    Text("Login")
                }
            }

            //adding login variable and description variable
            if (showLogin){
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 80.dp, end = 18.dp)
                        .size(width = 250.dp, height = 330.dp)
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
                        TextField(
                            value = usernameText,
                            onValueChange = {
                                usernameText = it
                            },
                            placeholder = {
                                Text("Username")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
                        )
                        TextField(
                            value = passwordText,
                            onValueChange = {
                                passwordText = it
                            },
                            placeholder = {
                                Text("Password")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp)
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
                        Button(
                            onClick = {
                                isRegistering = true;
                                showLogin = false;
                            },
                            modifier = Modifier.padding(top = 10.dp)
                        ) {
                            Text("Register")
                        }
                    }
                }
            }
            //Enables hazard description box if user is logged in
            if(isRegistering){
               
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
                                text = "Register an Account",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            TextField(
                                value = usernameRegText,
                                onValueChange = {
                                    usernameRegText = it
                                },
                                placeholder = {
                                    Text("Username")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp)
                            )
                            TextField(
                                value = passwordRegText,
                                onValueChange = {
                                    passwordRegText = it
                                },
                                placeholder = {
                                    Text("Password")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp)
                            )
                            Button(
                                onClick = {
                                    try {
                                       AccountValidator.validate(usernameRegText, passwordRegText)
                                       isRegistering = false;
                                    } catch (e: IllegalArgumentException) {
                                        
                                    }
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .align(Alignment.End)
                            ){
                                Text("Submit")
                            }
                        }
                    }
                }
            
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
            if (!showReportBox) {
    val allReports = repo.getAllReports()
    val reportsPerPage = 1
    val pageStart = reportPage * reportsPerPage
    val displayedReports = allReports
        .drop(pageStart)
        .take(reportsPerPage)

    Column(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(end = 16.dp, bottom = 16.dp)
            .width(280.dp)
            .height(180.dp)
    ) {
        displayedReports.forEach { report ->
            Text(
                text = buildString {
                    append("${report.type}: ${report.description}")
                    append("\nLocation: ${report.lat}, ${report.lng}")

                    if (report.image != null) {
                        append("\nImage: ${report.image}")
                    } else {
                        append("\nImage: None")
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        if (allReports.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    enabled = reportPage > 0,
                    onClick = { reportPage-- }
                ) {
                    Text("Previous")
                }

                Button(
                    enabled = (reportPage + 1) < allReports.size,
                    onClick = { reportPage++ }
                ) {
                    Text("Next")
                }
            }
        }
    }
}
        }
    }
}
