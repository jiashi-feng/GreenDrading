package com.greendrading.app.ui.message

data class RecommendProduct(
    val id: String,
    val imageRes: Int, // 本地图片资源id
    val title: String,
    val price: String,
    val userCount: String,
    val location: String,
    val tag: String
) 