import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.buildkonfig)
    kotlin("native.cocoapods")
}
val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}
// Read properties passed via command line (-P) or fallback locally
val mapsKey = providers.gradleProperty("MAPS_API_KEY")
    .orElse(providers.provider { localProperties.getProperty("MAPS_API_KEY") })
    .getOrElse("LOCAL_DEV_MAPS_KEY")

val iosMapsKey = providers.gradleProperty("IOS_MAPS_API_KEY")
    .orElse(providers.provider { localProperties.getProperty("IOS_MAPS_API_KEY") })
    .getOrElse("LOCAL_DEV_IOS_KEY")

val desktopKey = providers.gradleProperty("DESKTOP_API_KEY")
    .orElse(providers.provider { localProperties.getProperty("DESKTOP_API_KEY") })
    .getOrElse("LOCAL_DEV_DESKTOP_KEY")

buildkonfig {
    packageName = "edu.sdsu.cs250.team5.road_watch"
    exposeObjectWithName = "BuildKonfig"

    defaultConfigs {
        // Enclose values in single-escaped quotes for proper string literals
        buildConfigField(STRING, "IOS_API_KEY", iosMapsKey)
        buildConfigField(STRING, "DESKTOP_API_KEY", desktopKey)
        buildConfigField(STRING, "MAPS_API_KEY", mapsKey)
    }
}
kotlin {

    iosArm64()
    iosSimulatorArm64()



    cocoapods {
        summary = "Roadwatch"
        homepage = "https://github.com/CS250-Fall-2026-Team-5/RoadWatch"
        version = "1.0"
        ios.deploymentTarget = "15.0"

        framework {
            baseName = "Shared"
            isStatic = true
        }

        pod("GoogleMaps") {
            version = "8.4.0"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
    }

    jvm()
    
    android {
       namespace = "edu.sdsu.cs250.team5.road_watch.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()


        compilerOptions {
           jvmTarget = JvmTarget.JVM_11
       }
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
       withDeviceTestBuilder {
           sourceSetTreeName = "test"
       }.configure {
           instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
       }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.compose.uiTooling)
            implementation("com.google.android.gms:play-services-maps:20.0.0")
            implementation("dev.jordond.compass:geolocation-android-gms:4.0.0")
            implementation("com.google.maps.android:maps-compose:8.4.0")
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
        }

        jvmMain.dependencies {
            implementation("org.xerial:sqlite-jdbc:3.53.4.0")
        }

        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.swmansion.kmpMaps.core)
            implementation("dev.jordond.compass:geolocation:4.0.0")
            implementation("androidx.lifecycle:lifecycle-viewmodel:2.11.0")
            implementation("dev.jordond.compass:geocoder:4.0.0")
            implementation("dev.jordond.compass:geocoder-web-googlemaps:4.0.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            implementation("dev.jordond.compass:geolocation-mobile:4.0.0")
        }
        jvmMain.dependencies {
            implementation("dev.jordond.compass:geocoder-jvm:4.0.0")
            implementation("dev.jordond.compass:geocoder-web-googlemaps-jvm:4.0.0")
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}