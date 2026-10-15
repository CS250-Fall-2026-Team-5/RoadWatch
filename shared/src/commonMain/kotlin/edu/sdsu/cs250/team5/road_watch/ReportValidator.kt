package edu.sdsu.cs250.team5.road_watch

object ReportValidator {
    const val MAX_DESCRIPTION_LENGTH = 500

    private val supportedImageExtensions =
        setOf(".jpg", ".jpeg", ".png")

    fun validate(
        description: String,
        lat: Double,
        lng: Double,
        image: String?
    ): String? {
        val cleanedDescription = description.trim()

        if (cleanedDescription.isBlank()) {
            return "Description must not be blank"
        }

        if (cleanedDescription.length > MAX_DESCRIPTION_LENGTH) {
            return "Description must not exceed 500 characters"
        }

        if (lat !in -90.0..90.0) {
            return "Latitude must be between -90 and 90"
        }

        if (lng !in -180.0..180.0) {
            return "Longitude must be between -180 and 180"
        }

        if (image != null) {
            val imageName = image.trim().lowercase()

            if (supportedImageExtensions.none {
                    imageName.endsWith(it)
                }
            ) {
                return "Only JPG, JPEG, and PNG images are supported"
            }
        }

        return null
    }
}