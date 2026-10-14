import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}
val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":shared"))

    implementation(libs.androidx.activity.compose)

    implementation(libs.compose.uiToolingPreview)
    debugImplementation(libs.compose.uiTooling)
}
val mapsKey = providers.gradleProperty("MAPS_API_KEY")
    .orElse(providers.provider { localProperties.getProperty("MAPS_API_KEY") })
    .getOrElse("LOCAL_DEV_MAPS_KEY")


android {
    namespace = "edu.sdsu.cs250.team5.road_watch"
    compileSdk = libs.versions.android.compileSdk.get().toInt()


    defaultConfig {
        applicationId = "edu.sdsu.cs250.team5.road_watch"
        minSdk = 24
        targetSdk = 35
        manifestPlaceholders["MAPS_API_KEY"] = mapsKey
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}