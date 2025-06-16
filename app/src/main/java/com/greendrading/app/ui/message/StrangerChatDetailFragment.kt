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

class StrangerChatDetailFragment : Fragment() {

    private var _binding: FragmentStrangerChatDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var chatAdapter: ChatAdapter
    private val messages = mutableListOf<ChatMessage>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStrangerChatDetailBinding.inflate(inflater, container, false)
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
        loadStrangerMessages()
    }

    private fun setupRecyclerView() {
        chatAdapter = ChatAdapter(messages)
        binding.rvChatMessages.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chatAdapter
        }
    }

    private fun loadStrangerMessages() {
        messages.clear()
        messages.add(ChatMessage(
            id = "1",
            senderId = "stranger",
            content = "",
            timestamp = "12:00",
            isSentByUser = false,
            avatarRes = R.drawable.avatar_stranger,
            imageRes = R.drawable.wuliu3
        ))
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

    override fun onDestroyView() {
        super.onDestroyView()
        // Show bottom navigation again
        (activity as? MainActivity)?.showBottomNavigation()
        _binding = null
    }
} 