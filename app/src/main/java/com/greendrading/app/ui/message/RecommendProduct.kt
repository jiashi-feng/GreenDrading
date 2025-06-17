package com.greendrading.app.ui.message

/**
 * 推荐商品数据类
 * 用于存储消息页面中推荐商品的详细信息
 *
 * @property id 商品唯一标识
 * @property imageRes 商品图片资源ID
 * @property title 商品标题
 * @property price 商品价格
 * @property userCount 购买人数（如："已有1000+人购买"）
 * @property location 商品所在地
 * @property tag 商品标签（如："热销"、"新品"等）
 */
data class RecommendProduct(
    val id: String,
    val imageRes: Int, // 本地图片资源id
    val title: String,
    val price: String,
    val userCount: String,
    val location: String,
    val tag: String
) 