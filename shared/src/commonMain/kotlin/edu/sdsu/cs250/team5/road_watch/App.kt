package edu.sdsu.cs250.team5.road_watch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

import roadwatch.shared.generated.resources.Res
import roadwatch.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember {
            mutableStateOf(false)
        }

        val controller = remember {
            ReportSubmissionController()
        }

        var description by remember {
            mutableStateOf("")
        }

        var latitude by remember {
            mutableStateOf("")
        }

        var longitude by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = {
                    showContent = !showContent
                }
            ) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                val greeting = remember {
                    Greeting().greet()
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(
                            Res.drawable.compose_multiplatform
                        ),
                        contentDescription = null
                    )

                    Text("Compose: $greeting")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Report a Road Hazard",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                },
                label = {
                    Text("Description")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = latitude,
                onValueChange = {
                    latitude = it
                },
                label = {
                    Text("Latitude")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = longitude,
                onValueChange = {
                    longitude = it
                },
                label = {
                    Text("Longitude")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    controller.submitReport(
                        type = HazardType.POTHOLE,
                        description = description,
                        lat = latitude.toDoubleOrNull()
                            ?: Double.NaN,
                        lng = longitude.toDoubleOrNull()
                            ?: Double.NaN
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Report")
            }

            Spacer(modifier = Modifier.height(12.dp))

            controller.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error
                )
            }

            controller.submittedReport?.let {
                Text(
                    text = "Report submitted successfully!",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
