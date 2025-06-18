package com.greendrading.app.ui.dailyshopping

data class Coupon(
    val id: String,
    val imageRes: Int, // 例如：R.drawable.coupon_plant_image
    val category: String, // 例如：绿植299-100券
    val value: String, // 例如：¥100
    val condition: String, // 例如：满299可用
    val timeLeft: String, // 例如：00:28:12
    val available: Boolean = true // 是否可用
) 