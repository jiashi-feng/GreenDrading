package com.greendrading.app.ui.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendrading.app.R
import com.greendrading.app.databinding.FragmentInvitationDetailBinding

class InvitationDetailFragment : Fragment() {

    private var _binding: FragmentInvitationDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var invitationAdapter: InvitationAdapter
    private val invitationList: MutableList<InvitationItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvitationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadInvitations()
        setupListeners()
    }

    private fun setupRecyclerView() {
        invitationAdapter = InvitationAdapter(
            invitations = invitationList,
            onReplyClick = { invitation ->
                Toast.makeText(context, "回复 ${invitation.userName}", Toast.LENGTH_SHORT).show()
            },
            onCloseClick = { invitation ->
                // 模拟删除操作
                val position = invitationList.indexOfFirst { it.id == invitation.id }
                if (position != -1) {
                    invitationList.removeAt(position)
                    invitationAdapter.updateInvitations(invitationList)
                    Toast.makeText(context, "已删除", Toast.LENGTH_SHORT).show()
                }
            }
        )
        binding.rvInvitations.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = invitationAdapter
        }
    }

    private fun loadInvitations() {
        invitationList.clear()
        invitationList.addAll(listOf(
            InvitationItem(
                id = "inv1",
                userAvatarRes = R.drawable.avatar_mother, // 惊鸿一梦的头像
                userName = "惊鸿一梦",
                question = "送长辈可以嘛？",
                productImgRes = R.drawable.p1,
                productName = "红掌盆栽水培植物室内好养一帆风顺...",
                productPrice = "¥ 59.8",
                timeAndReplies = "4天前 共8条回复"
            ),
            InvitationItem(
                id = "inv2",
                userAvatarRes = R.drawable.avatar_stranger, // 开心的小熊的头像
                userName = "开心的小熊",
                question = "送女朋友可以嘛？",
                productImgRes = R.drawable.p2,
                productName = "超香姬蒲草四季麦冬开花浓香型盆栽...",
                productPrice = "¥ 59.8",
                timeAndReplies = "4天前 共8条回复"
            ),
            InvitationItem(
                id = "inv3",
                userAvatarRes = R.drawable.avatar_shop, // 南苑的花圃的头像
                userName = "南苑的花圃",
                question = "香味会不会很刺鼻？",
                productImgRes = R.drawable.p3,
                productName = "超香姬蒲草四季麦冬开花浓香型盆栽...",
                productPrice = "¥ 59.8",
                timeAndReplies = "4天前 共8条回复"
            ),
            InvitationItem(
                id = "inv4",
                userAvatarRes = R.drawable.avatar_child, // 奋进的小鸟的头像
                userName = "奋进的小鸟",
                question = "这种杜鹃装饰花园好搭配嘛？",
                productImgRes = R.drawable.p4,
                productName = "杜鹃花盆栽，四季开花，好养易活...",
                productPrice = "¥ 39.9",
                timeAndReplies = "5天前 共12条回复"
            ),
            InvitationItem(
                id = "inv5",
                userAvatarRes = R.drawable.avatar_mother, // 惊鸿一梦的头像 (示例)
                userName = "惊鸿一梦",
                question = "送长辈可以嘛？",
                productImgRes = R.drawable.p5,
                productName = "超香姬蒲草四季麦冬开花浓香型盆栽...",
                productPrice = "¥ 59.8",
                timeAndReplies = "4天前 共8条回复"
            ),
            InvitationItem(
                id = "inv6",
                userAvatarRes = R.drawable.avatar_stranger, // 开心的小熊的头像 (示例)
                userName = "开心的小熊",
                question = "送女朋友可以嘛？",
                productImgRes = R.drawable.p6,
                productName = "红掌盆栽水培植物室内好养一帆风顺...",
                productPrice = "¥ 59.8",
                timeAndReplies = "4天前 共8条回复"
            ),
            InvitationItem(
                id = "inv7",
                userAvatarRes = R.drawable.avatar_shop, // 南苑的花圃的头像 (示例)
                userName = "南苑的花圃",
                question = "香味会不会很刺鼻？",
                productImgRes = R.drawable.p7,
                productName = "超香姬蒲草四季麦冬开花浓香型盆栽...",
                productPrice = "¥ 59.8",
                timeAndReplies = "4天前 共8条回复"
            ),
            InvitationItem(
                id = "inv8",
                userAvatarRes = R.drawable.avatar_child, // 奋进的小鸟的头像 (示例)
                userName = "奋进的小鸟",
                question = "这种杜鹃装饰花园好搭配嘛？",
                productImgRes = R.drawable.p8,
                productName = "杜鹃花盆栽，四季开花，好养易活...",
                productPrice = "¥ 39.9",
                timeAndReplies = "5天前 共12条回复"
            )
        ))
        invitationAdapter.updateInvitations(invitationList)
    }

    private fun setupListeners() {
        binding.btnBackInvitationDetail.setOnClickListener { 
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 