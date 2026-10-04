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
        var searchText by remember {
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
                placeHolder = {
                    Text("Search maps")
                },
                    modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(18.dp)
                    Text("Search Maps")
            )
                //hide login screen
        if (showLogin){
            Box(
                modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 80.dp, end = 18.dp)
                .size(width = 250.dp, height = 280.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant
                    )
                ){
                Text(
                    text = "Login",
                    modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 18.dp)
                    )
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
                            }
                            )
                    }
                    )
                }
        }
    }
}
                
                    
