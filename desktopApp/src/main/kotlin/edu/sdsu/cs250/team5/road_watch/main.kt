package edu.sdsu.cs250.team5.road_watch

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.datlag.kcef.KCEF
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "RoadWatch",
    ) {
        var initialized by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(Unit) {
            withContext(Dispatchers.IO) {
                KCEF.init(
                    builder = {
                        installDir(File("kcef-bundle"))
                        release(true)

                        progress {
                            onInitialized {
                                initialized = true
                            }
                        }

                        settings {
                            noSandbox = true
                        }
                    },
                    onError = {
                        it?.printStackTrace()
                    },
                )
            }
        }

        if (initialized) {
            App()
        } else {
            BasicText("Initializing Map Engine...")
        }
    }
}
