package edu.sdsu.cs250.team5.road_watch
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import  androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
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

@Composable
@Preview
fun App() {
    MaterialTheme{
        //Creating basic box background
        Box( modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
           ) {
            //Creating temporary placeholder map
            Text(
                text = "Map Placeholder",
                modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp)
                )
                //Search bar creation top right
                Button(
                    onClick = {
                        //Ability to click no search function yet
                    },
                    modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(18.dp)
                    ){
                    Text("Search Maps")
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
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                    ){
                
                //Implementing skeleton pin and logic
                Text(
                    text = "|",
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
                
                    
