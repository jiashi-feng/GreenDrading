package com.greendrading.app.ui.productdetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendrading.app.R
import com.greendrading.app.databinding.ActivityBegoniaDetailBinding
import com.greendrading.app.ui.message.MessageType

class BegoniaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBegoniaDetailBinding
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBegoniaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()
        loadChatMessages()
    }

    private fun setupViews() {
        chatAdapter = ChatAdapter(emptyList())
        binding.rvChatMessages.apply {
            layoutManager = LinearLayoutManager(this@BegoniaDetailActivity)
            adapter = chatAdapter
        }
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnViewDetails.setOnClickListener {
            Toast.makeText(this, "查看详情功能开发中", Toast.LENGTH_SHORT).show()
        }

        binding.btnPlus.setOnClickListener {
            Toast.makeText(this, "更多功能开发中", Toast.LENGTH_SHORT).show()
        }

        binding.btnVoiceInput.setOnClickListener {
            Toast.makeText(this, "语音输入功能开发中", Toast.LENGTH_SHORT).show()
        }

        binding.btnEmoji.setOnClickListener {
            Toast.makeText(this, "表情功能开发中", Toast.LENGTH_SHORT).show()
        }

        binding.btnReport.setOnClickListener {
            Toast.makeText(this, "举报成功", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadChatMessages() {
        val messages = listOf(
            ChatMessage(senderId = "1", content = "海棠花算是比较好养活的植物啦，只要稍微上点心就能让它健康生长。", isSelf = false, avatarRes = R.drawable.photo1),
            ChatMessage(senderId = "2", content = "喜欢阳光充足但别暴晒的地方，浇水别太勤，土壤保持湿润不积水就行。", isSelf = false, avatarRes = R.drawable.photo2),
            ChatMessage(senderId = "3", content = "夏天太热时适当遮阴，冬天怕冷的话搬进室内保暖。", isSelf = false, avatarRes = R.drawable.photo3),
            ChatMessage(senderId = "4", content = "平时注意通风，偶尔施点薄肥", isSelf = false, avatarRes = R.drawable.photo4)
        )
        chatAdapter.updateMessages(messages)
    }
} 