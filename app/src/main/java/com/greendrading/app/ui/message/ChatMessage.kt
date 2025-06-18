package com.greendrading.app.ui.message

/**
 * 聊天消息数据类
 * 用于存储单条聊天消息的详细信息，支持文本消息和图片消息
 *
 * @property id 消息唯一标识
 * @property senderId 发送者ID，用于标识消息的发送者，可以是用户ID或特定字符串（如"robot"、"system"等）
 * @property content 消息文本内容
 * @property timestamp 消息发送时间
 * @property isSentByUser 是否为当前用户发送的消息
 * @property avatarRes 发送者头像资源ID
 * @property imageRes 图片消息的资源ID，如果是文本消息则为null
 */
data class ChatMessage(
    val id: String,
    val senderId: String, // 用于标识发送者，可以是用户ID或特定字符串
    val content: String,
    val timestamp: String,
    val isSentByUser: Boolean, // true表示是当前用户发送的消息，false表示是对方发送的消息
    val avatarRes: Int, // 发送者的头像资源ID
    val imageRes: Int? = null // 图片消息的资源ID，可选
) 