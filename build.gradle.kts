import java.util.Properties
plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidMultiplatformLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}
val localProperties = Properties()

val localPropertiesFile = rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use { stream ->
        localProperties.load(stream)
    }
}
plugins.withType<com.android.build.gradle.AppPlugin> {
    extensions.configure<com.android.build.api.dsl.ApplicationExtension> {
        val MAPS_API_KEY: String = project.findProperty("MAPS_API_KEY")?.toString() ?: "PLACEHOLDER_KEY"
        defaultConfig.manifestPlaceholders["MAPS_API_KEY"] = MAPS_API_KEY
    }
}
