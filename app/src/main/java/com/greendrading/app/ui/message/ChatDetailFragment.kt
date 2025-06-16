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

class ChatDetailFragment : Fragment() {

    private var _binding: FragmentChatDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var chatAdapter: ChatAdapter
    private val chatMessageList: MutableList<ChatMessage> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE
        setupRecyclerView()
        loadChatMessages()
        setupListeners()
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(chatMessageList)
        binding.rvChatMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
    }

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

    private fun setupListeners() {
        binding.btnBackChatDetail.setOnClickListener { 
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

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

        binding.btnEmoji.setOnClickListener {
            Toast.makeText(context, "表情功能开发中", Toast.LENGTH_SHORT).show()
        }

        binding.btnVoice.setOnClickListener {
            Toast.makeText(context, "语音功能开发中", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (activity as? MainActivity)?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE
    }
} 