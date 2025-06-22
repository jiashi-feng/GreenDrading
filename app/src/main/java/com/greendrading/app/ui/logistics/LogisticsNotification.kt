package com.greendrading.app.ui.logistics

sealed class LogisticsNotification {
    data class SignedNotification(
        val id: String,
        val date: String,
        val message: String,
        val imageUrl: Int, // Placeholder for image resource ID
        val viewLogisticsButtonText: String = "查看物流",
        val subNotifications: List<String> = emptyList(),
        var isExpanded: Boolean = false
    ) : LogisticsNotification()

    data class OrderStatusUpdateNotification(
        val id: String,
        val date: String,
        val mainMessage: String,
        val imageUrl: Int,
        val viewProgressButtonText: String = "查看审核进度",
        val subNotifications: List<String>,
        var isExpanded: Boolean = false
    ) : LogisticsNotification()

    data class ShippingNotification(
        val id: String,
        val date: String,
        val mainMessage: String,
        val imageUrl: Int, // Placeholder for image resource ID
        val subNotifications: List<String>, // List of sub-notifications like "商品发货通知"
        val viewProgressButtonText: String = "查看审核进度",
        var isExpanded: Boolean = false
    ) : LogisticsNotification()

    data class PickupReminderNotification(
        val id: String,
        val date: String,
        val message: String,
        val imageUrl: Int, // Placeholder for image resource ID
        val viewPickupButtonText: String = "查看取件信息",
        val subNotifications: List<String> = emptyList(),
        var isExpanded: Boolean = false
    ) : LogisticsNotification()
} 