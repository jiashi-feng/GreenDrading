package com.greendrading.app.ui.message

data class ChatMessage(
    val id: String,
    val senderId: String, // 用于标识发送者，可以是用户ID或特定字符串
    val content: String,
    val timestamp: String,
    val isSentByUser: Boolean, // true表示是当前用户发送的消息，false表示是对方发送的消息
    val avatarRes: Int, // 发送者的头像资源ID
    val imageRes: Int? = null // 图片消息的资源ID，可选
) 