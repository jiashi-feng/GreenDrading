package com.greendrading.app.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendrading.app.MainActivity
import com.greendrading.app.R
import com.greendrading.app.databinding.FragmentKnowledgeQaDetailBinding

/**
 * 知识问答详情页面Fragment
 * 用于显示与智能问答助手的对话界面，主要处理植物相关的咨询问题
 */
class KnowledgeQaDetailFragment : Fragment() {

    // ViewBinding相关变量
    private var _binding: FragmentKnowledgeQaDetailBinding? = null
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
        _binding = FragmentKnowledgeQaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * 视图创建完成后的初始化
     * 包括隐藏底部导航栏、设置返回按钮、初始化聊天列表等
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
        loadKnowledgeQaMessages()
    }

    /**
     * 设置RecyclerView和适配器
     * 用于显示问答消息列表
     */
    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(messages)
        binding.rvChatMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
    }

    /**
     * 加载知识问答消息
     * 这里使用模拟数据，实际应用中应该从数据库或网络加载
     * 目前只包含一条机器人助手的欢迎消息
     */
    private fun loadKnowledgeQaMessages() {
        messages.clear()
        // 添加机器人助手的欢迎消息
        messages.add(ChatMessage(
            id = "1",
            senderId = "robot",
            content = "你好！我是智能问答助手。有任何关于植物的问题均可以向我提问！",
            timestamp = "12:00",
            isSentByUser = false,
            avatarRes = R.drawable.avatar_robot
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