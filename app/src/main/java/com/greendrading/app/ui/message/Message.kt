package com.greendrading.app.ui.message

/**
 * 消息类型枚举
 * ALL: 所有消息
 * UNREAD: 未读消息
 * NOTIFICATION: 通知类消息（如系统通知、物流通知等）
 * PRIVATE: 私信类消息（如聊天消息、邀请消息等）
 */
enum class MessageType {
    ALL,
    UNREAD,
    NOTIFICATION,
    PRIVATE
}

/**
 * 消息跳转类型枚举
 * NONE: 无跳转
 * LOGISTICS: 跳转到物流详情
 * CHAT: 跳转到聊天详情
 * DISCUSS: 跳转到讨论详情
 * COUPON: 跳转到优惠券页面
 */
enum class MessageJumpType {
    NONE, LOGISTICS, CHAT, DISCUSS, COUPON
}

/**
 * 消息数据类
 * 用于存储消息的基本信息，包括发送者、内容、时间等
 *
 * @property id 消息唯一标识
 * @property avatarRes 发送者头像资源ID
 * @property sender 发送者名称
 * @property content 消息内容
 * @property time 发送时间
 * @property unread 是否未读
 * @property type 消息类型
 * @property jumpType 消息跳转类型
 * @property extra 额外信息，可用于传递商品id、活动id等
 */
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