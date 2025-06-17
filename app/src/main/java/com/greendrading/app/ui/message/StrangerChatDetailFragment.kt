package com.greendrading.app.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendrading.app.MainActivity
import com.greendrading.app.R
import com.greendrading.app.databinding.FragmentStrangerChatDetailBinding

/**
 * 陌生人聊天详情页面Fragment
 * 用于显示与陌生人的聊天记录，主要处理商品咨询等场景
 */
class StrangerChatDetailFragment : Fragment() {

    // ViewBinding相关变量
    private var _binding: FragmentStrangerChatDetailBinding? = null
    private val binding get() = _binding!!
    
    // 聊天适配器和消息列表
    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<ChatMessage>()

    /**
     * 创建Fragment视图
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStrangerChatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * 视图创建完成后的初始化
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 隐藏底部导航栏
        (activity as? MainActivity)?.hideBottomNavigation()

        // 设置返回按钮点击事件
        binding.toolbar.setNavigationOnClickListener { 
            requireActivity().onBackPressed() 
        }

        setupRecyclerView()
        loadStrangerMessages()
    }

    /**
     * 设置RecyclerView和适配器
     * 用于显示聊天消息列表
     */
    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(messages)
        binding.rvChatMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
    }

    /**
     * 加载陌生人聊天消息
     * 这里使用模拟数据，实际应用中应该从数据库或网络加载
     * 包含图片消息和文本消息两种类型
     */
    private fun loadStrangerMessages() {
        messages.clear()
        // 添加图片消息
        messages.add(ChatMessage(
            id = "1",
            senderId = "stranger",
            content = "",
            timestamp = "12:00",
            isSentByUser = false,
            avatarRes = R.drawable.avatar_stranger,
            imageRes = R.drawable.wuliu3
        ))
        // 添加文本消息
        messages.add(ChatMessage(
            id = "2",
            senderId = "stranger",
            content = "你好，这个玫瑰花怎么样？",
            timestamp = "12:01",
            isSentByUser = false,
            avatarRes = R.drawable.avatar_stranger
        ))
        chatAdapter.notifyDataSetChanged()
        binding.rvChatMessages.scrollToPosition(messages.size - 1)
    }

    /**
     * Fragment销毁时清理资源
     * 显示底部导航栏并释放ViewBinding
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // 显示底部导航栏
        (activity as? MainActivity)?.showBottomNavigation()
        _binding = null
    }
} 