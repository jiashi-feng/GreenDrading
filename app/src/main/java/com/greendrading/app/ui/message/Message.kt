package com.greendrading.app.ui.message

enum class MessageType {
    ALL,
    UNREAD,
    NOTIFICATION,
    PRIVATE
}

enum class MessageJumpType {
    NONE, LOGISTICS, CHAT, DISCUSS, COUPON
}

data class Message(
    val id: String,
    val avatarRes: Int, // 本地图片资源id
    val sender: String,
    val content: String,
    val time: String,
    val unread: Boolean,
    val type: MessageType,
    val jumpType: MessageJumpType = MessageJumpType.NONE,
    val extra: String? = null // 可用于传递商品id、活动id等
) 