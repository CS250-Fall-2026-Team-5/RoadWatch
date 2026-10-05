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
        var descriptionError by remember {
            mutableStateOf("")
        }
        
        
        //Creating basic box background main screen hud
        Box( modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
           ) {
            //Creating barebones search box for searching through google maps
            TextField(
                //value of search box changes to user prompt
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                placeholder = {
                    Text("Search maps")
                },
                    modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(20.dp)
                    )
                //adding login variable and description variable, however can not store user description for now
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
                .align(Alignment.Center)
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
                            if (it.isEmpty()){
                                descriptionError = "Description cannot be blank, please try again."
                            }
                            else if (it.length > 500) {
                                descriptionError = "Description cannot be more than 500 letters."
                            }
                            else {
                                descriptionError = ""
                            }
                        },
                        placeholder = {
                            Text("Enter your description..")
                        },
                        modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                        )
                    Button(
                        onClick = {
                            showReportBox = false
                            hazardDescription = ""
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
        }
    }
}
                
                    
