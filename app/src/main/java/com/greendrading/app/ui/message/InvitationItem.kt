package com.greendrading.app.ui.message

/**
 * 商品咨询邀请数据类
 * 用于存储用户对商品的咨询信息，包含用户信息和商品信息
 *
 * @property id 邀请唯一标识
 * @property userAvatarRes 咨询用户的头像资源ID
 * @property userName 咨询用户的名称
 * @property question 用户咨询的问题内容
 * @property productImgRes 相关商品的图片资源ID
 * @property productName 相关商品的名称
 * @property productPrice 相关商品的价格
 * @property timeAndReplies 咨询时间和回复数量信息（如："4天前 共8条回复"）
 */
data class InvitationItem(
    val id: String,
    val userAvatarRes: Int,
    val userName: String,
    val question: String,
    val productImgRes: Int,
    val productName: String,
    val productPrice: String,
    val timeAndReplies: String
) 