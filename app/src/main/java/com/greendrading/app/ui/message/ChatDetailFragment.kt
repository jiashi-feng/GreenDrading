package com.greendrading.app.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendrading.app.R
import com.greendrading.app.MainActivity
import com.greendrading.app.databinding.FragmentChatDetailBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * 聊天详情页面Fragment
 * 用于显示与特定联系人的聊天记录和发送新消息
 */
class ChatDetailFragment : Fragment() {

    // ViewBinding相关变量
    private var _binding: FragmentChatDetailBinding? = null
    private val binding get() = _binding!!

    // 聊天适配器和消息列表
    private lateinit var chatAdapter: ChatAdapter
    private val chatMessageList: MutableList<ChatMessage> = mutableListOf()

    /**
     * 创建Fragment视图
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * 视图创建完成后的初始化
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 隐藏底部导航栏
        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        setupRecyclerView()
        loadChatMessages()
        setupListeners()
    }

    /**
     * 设置RecyclerView和适配器
     */
    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(chatMessageList)
        binding.rvChatMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
    }

    /**
     * 加载聊天消息数据
     * 这里使用模拟数据，实际应用中应该从数据库或网络加载
     */
    private fun loadChatMessages() {
        chatMessageList.clear()
        chatMessageList.addAll(listOf(
            ChatMessage(
                id = "chat1",
                senderId = "mom",
                content = "我逛绿韵花坊的时候看到很多植物，有海棠花，玫瑰花啥的都挺好看的",
                timestamp = "12:00",
                isSentByUser = false,
                avatarRes = R.drawable.hh1 // 妈妈的头像
            ),
            ChatMessage(
                id = "chat2",
                senderId = "me",
                content = "我刚去找了一家，挺好的",
                timestamp = "12:01",
                isSentByUser = true,
                avatarRes = R.drawable.hh2 // 我的头像
            ),
            ChatMessage(
                id = "chat3",
                senderId = "mom",
                content = "是吧，感觉看植物生长，让人充满生命力，心情愉悦！",
                timestamp = "12:02",
                isSentByUser = false,
                avatarRes = R.drawable.hh1 // 妈妈的头像
            ),
            ChatMessage(
                id = "chat4",
                senderId = "me",
                content = "要不咱家也养一盆花吧",
                timestamp = "12:03",
                isSentByUser = true,
                avatarRes = R.drawable.hh2 // 我的头像
            ),
            ChatMessage(
                id = "chat5",
                senderId = "mom",
                content = "可以啊，你再对比一下其他商家的，植物的状态，评论啥的。",
                timestamp = "12:04",
                isSentByUser = false,
                avatarRes = R.drawable.hh1 // 妈妈的头像
            ),
            ChatMessage(
                id = "chat6",
                senderId = "me",
                content = "那我们就买这家吧，他家好评挺多的。",
                timestamp = "12:05",
                isSentByUser = true,
                avatarRes = R.drawable.hh2 // 我的头像
            )
        ))
        chatAdapter.notifyDataSetChanged()
        binding.rvChatMessages.scrollToPosition(chatMessageList.size - 1) // 滚动到最新消息
    }

    /**
     * 设置各种按钮的点击监听器
     */
    private fun setupListeners() {
        // 返回按钮点击事件
        binding.btnBackChatDetail.setOnClickListener { 
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // 发送消息按钮点击事件
        binding.btnSendMore.setOnClickListener { 
            val messageContent = binding.etMessageInput.text.toString().trim()
            if (messageContent.isNotEmpty()) {
                val newMessage = ChatMessage(
                    id = System.currentTimeMillis().toString(),
                    senderId = "me", // 假设是当前用户发送
                    content = messageContent,
                    timestamp = "现在", // 简化时间戳
                    isSentByUser = true,
                    avatarRes = R.drawable.hh2 // 当前用户头像
                )
                chatAdapter.addMessage(newMessage)
                binding.etMessageInput.text.clear()
                binding.rvChatMessages.scrollToPosition(chatMessageList.size - 1)
            } else {
                Toast.makeText(context, "消息不能为空", Toast.LENGTH_SHORT).show()
            }
        }

        // 表情按钮点击事件
        binding.btnEmoji.setOnClickListener {
            Toast.makeText(context, "表情功能开发中", Toast.LENGTH_SHORT).show()
        }

        // 语音按钮点击事件
        binding.btnVoice.setOnClickListener {
            Toast.makeText(context, "语音功能开发中", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Fragment销毁时清理资源
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // 显示底部导航栏
        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE
    }
} 