package com.greendrading.app.ui.logistics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendrading.app.R
import com.greendrading.app.databinding.ActivityLogisticsBinding

class LogisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogisticsBinding
    private lateinit var adapter: LogisticsNotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()
        loadLogisticsData()
    }

    private fun setupViews() {
        adapter = LogisticsNotificationAdapter(emptyList()) {
            // Handle item click
            Toast.makeText(this, "点击了物流通知", Toast.LENGTH_SHORT).show()
        }
        binding.rvLogisticsNotifications.apply {
            layoutManager = LinearLayoutManager(this@LogisticsActivity)
            adapter = this@LogisticsActivity.adapter
        }
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnMore.setOnClickListener {
            Toast.makeText(this, "更多选项", Toast.LENGTH_SHORT).show()
        }

        binding.btnCheckExpress.setOnClickListener {
            Toast.makeText(this, "查快递", Toast.LENGTH_SHORT).show()
        }

        binding.btnSendExpress.setOnClickListener {
            Toast.makeText(this, "寄快递", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogisticsCustomerService.setOnClickListener {
            Toast.makeText(this, "物流客服", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadLogisticsData() {
        val notifications = listOf(
            LogisticsNotification.SignedNotification(
                id = "1",
                date = "星期日",
                message = "韵达快递显示您的商品已签收",
                imageUrl = R.drawable.wuliu1,
                subNotifications = emptyList() // 订单签收通知没有子消息
            ),
            LogisticsNotification.OrderStatusUpdateNotification(
                id = "2",
                date = "星期日",
                mainMessage = "您的评价正在审核中，预计24小时内出审核结果，审核通过后可领取奖励",
                imageUrl = R.drawable.wuliu2,
                viewProgressButtonText = "查看审核进度",
                subNotifications = listOf("商品发货通知", "物流中转通知", "商品派送通知")
            ),
            LogisticsNotification.PickupReminderNotification(
                id = "3",
                date = "星期二",
                message = "您的商品在代收点已超1天未取。若取件遇到问题请联系快递员，今日共1个快递代取",
                imageUrl = R.drawable.wuliu3,
                subNotifications = emptyList() // 待取件提醒目前没有子消息，但可以扩展
            )
        )
        adapter.updateNotifications(notifications)
    }
} 