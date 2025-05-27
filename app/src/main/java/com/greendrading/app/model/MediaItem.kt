package com.greendrading.app.model

import android.net.Uri

data class MediaItem(
    val uri: Uri,
    val type: MediaType = MediaType.IMAGE
)

enum class MediaType {
    IMAGE,
    VIDEO
} 