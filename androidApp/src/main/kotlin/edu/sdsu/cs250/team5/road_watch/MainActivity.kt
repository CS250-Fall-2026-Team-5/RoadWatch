package edu.sdsu.cs250.team5.road_watch

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        try {
            val appInfo = packageManager.getApplicationInfo(
                packageName,
                PackageManager.GET_META_DATA
            )
            if (appInfo.metaData == null) {
                appInfo.metaData = Bundle()
            }

            appInfo.metaData.putString("com.google.android.geo.API_KEY", BuildKonfig.MAPS_API_KEY)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}