package com.greendrading.app.ui.productdetail

data class ChatMessage(
    val senderId: String,
    val content: String,
    val isSelf: Boolean,
    val avatarRes: Int = 0 // Add avatar resource ID, 0 as default if not provided
) 