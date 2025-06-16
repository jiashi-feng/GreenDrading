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

class KnowledgeQaDetailFragment : Fragment() {

    private var _binding: FragmentKnowledgeQaDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<ChatMessage>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKnowledgeQaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide bottom navigation
        (activity as? MainActivity)?.hideBottomNavigation()

        binding.toolbar.setNavigationOnClickListener { 
            requireActivity().onBackPressed() 
        }

        setupRecyclerView()
        loadKnowledgeQaMessages()
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(messages)
        binding.rvChatMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
    }

    private fun loadKnowledgeQaMessages() {
        messages.clear()
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

    override fun onDestroyView() {
        super.onDestroyView()
        // Show bottom navigation again
        (activity as? MainActivity)?.showBottomNavigation()
        _binding = null
    }
} 