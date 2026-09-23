import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.kotlinCocoapods)
}
val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localPropertiesFile.inputStream().use { load(it) }
    }
}
val MAPS_API_KEY: String = project.findProperty("MAPS_API_KEY").toString() ?: ""
buildkonfig {
    packageName = "edu.sdsu.cs250.team5.road_watch"
    defaultConfigs {
        val IOS_MAPS_API_KEY: String = project.findProperty("IOS_MAPS_API_KEY").toString() ?: ""
        val DESKTOP_API_KEY: String = project.findProperty("DESKTOP_API_KEY").toString() ?: ""

        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "IOS_MAPS_API_KEY" , IOS_MAPS_API_KEY)
        buildConfigField(com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING, "DESKTOP_API_KEY" , DESKTOP_API_KEY)
    }
}
kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    androidComponents {
        onVariants { variant ->
            variant.manifestPlaceholders.put("MAPS_API_KEY", MAPS_API_KEY)
        }
    }

    cocoapods {
        summary = "Class Project Map App"
        homepage = "https://github.com"
        version = "1.0"
        ios.deploymentTarget = "15.0"

        pod("GoogleMaps") {
            version = "8.4.0"
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
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}