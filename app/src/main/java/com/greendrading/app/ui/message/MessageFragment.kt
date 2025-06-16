package com.greendrading.app.ui.message

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.greendrading.app.R
import com.greendrading.app.databinding.FragmentMessageBinding
import com.greendrading.app.ui.productdetail.ProductDetailActivity

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private lateinit var messageAdapter: MessageListAdapter
    private lateinit var recommendAdapter: RecommendAdapter
    private var currentTab = 0 // 0: 通知, 1: 私信
    private var messageList: MutableList<Message> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, "Fragment已加载", Toast.LENGTH_SHORT).show()
        Log.d("MessageFragment", "onViewCreated called")
        setupViews()
        setupListeners()
        loadMessages()
        loadRecommend()
    }

    private fun setupViews() {
        // 消息列表
        messageAdapter = MessageListAdapter(emptyList(),
            onItemClick = { message ->
                // 点击消息，设为已读
                val idx = messageList.indexOfFirst { it.id == message.id }
                if (idx != -1 && messageList[idx].unread) {
                    messageList[idx] = messageList[idx].copy(unread = false)
                    messageAdapter.updateMessages(messageList)
                }
                if (message.sender == "底特律拳重娃娃") {
                    val intent = Intent(context, ProductDetailActivity::class.java)
                    startActivity(intent)
                } else if (message.sender == "海棠花，好养活吗？") {
                    val intent = Intent(context, com.greendrading.app.ui.productdetail.BegoniaDetailActivity::class.java)
                    startActivity(intent)
                } else if (message.sender == "每天逛逛") {
                    val intent = Intent(context, com.greendrading.app.ui.dailyshopping.DailyShoppingActivity::class.java)
                    startActivity(intent)
                } else if (message.sender == "物流助手") {
                    val intent = Intent(context, com.greendrading.app.ui.logistics.LogisticsActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(context, "进入详情：${message.sender}", Toast.LENGTH_SHORT).show()
                }
            },
            onActionClick = { message ->
                when (message.jumpType) {
                    MessageJumpType.LOGISTICS -> Toast.makeText(context, "查物流", Toast.LENGTH_SHORT).show()
                    MessageJumpType.COUPON -> Toast.makeText(context, "去领取优惠券", Toast.LENGTH_SHORT).show()
                    else -> {}
                }
            },
            onItemLongClick = { message ->
                showMessageOptions(message)
            }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = messageAdapter
        }
        binding.swipeRefresh.setOnRefreshListener { loadMessages() }

        // 推荐区
        recommendAdapter = RecommendAdapter(emptyList()) { product ->
            Toast.makeText(context, "跳转商品详情：${product.title}", Toast.LENGTH_SHORT).show()
        }
        binding.recommendRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendAdapter
        }
    }

    private fun setupListeners() {
        // Tab切换
        binding.tabLayout.removeAllTabs()
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("通知"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("私信"))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                currentTab = tab?.position ?: 0
                loadMessages()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        // 搜索按钮
        binding.btnSearch.setOnClickListener {
            Toast.makeText(context, "搜索功能开发中", Toast.LENGTH_SHORT).show()
        }
        // 全部已读
        binding.tvMarkAllRead.setOnClickListener {
            messageList = messageList.map { it.copy(unread = false) }.toMutableList()
            messageAdapter.updateMessages(messageList)
            Toast.makeText(context, "全部标记为已读", Toast.LENGTH_SHORT).show()
        }
        // 返回按钮
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun loadMessages() {
        messageList = mutableListOf(
            Message(
                id = "1",
                avatarRes = R.drawable.avatar_shop, // 商家头像
                sender = "底特律拳重娃娃",
                content = "等待卖家发货\n我们家栀子花都很好，亲可以放心购买",
                time = "1小时前",
                unread = true,
                type = MessageType.PRIVATE,
                jumpType = MessageJumpType.CHAT
            ),
            Message(
                id = "2",
                avatarRes = R.drawable.avatar_child, // 儿童头像
                sender = "海棠花，好养活吗？",
                content = "fore....不知名等6人回答了问题",
                time = "1小时前",
                unread = true,
                type = MessageType.PRIVATE,
                jumpType = MessageJumpType.DISCUSS
            ),
            Message(
                id = "3",
                avatarRes = R.drawable.default_avatar, // 修改为默认头像，由适配器根据jumpType显示类型图标
                sender = "每天逛逛",
                content = "领券小助手恭喜你有优惠券可领取",
                time = "4天前",
                unread = false,
                type = MessageType.NOTIFICATION,
                jumpType = MessageJumpType.COUPON
            ),
            Message(
                id = "4",
                avatarRes = R.drawable.default_avatar, // 修改为默认头像，由适配器根据jumpType显示类型图标
                sender = "物流助手",
                content = "商品已取件，请关注物流信息",
                time = "1天前",
                unread = false,
                type = MessageType.NOTIFICATION,
                jumpType = MessageJumpType.LOGISTICS
            )
        )
        messageAdapter.updateMessages(messageList)
        binding.swipeRefresh.isRefreshing = false
    }

    private fun loadRecommend() {
        val products = listOf(
            RecommendProduct(
                id = "p1",
                imageRes = R.drawable.succulents,
                title = "30颗多肉品种",
                price = "9.8元",
                userCount = "22人说价格真香",
                location = "上海",
                tag = "该用户信用良好"
            ),
            RecommendProduct(
                id = "p2",
                imageRes = R.drawable.potted_plant,
                title = "文竹绿植6.8超低价带盆",
                price = "6.8元",
                userCount = "自产自销价格真香",
                location = "山东",
                tag = "发货最快"
            )
        )
        recommendAdapter.updateProducts(products)
    }

    private fun showMessageOptions(message: Message) {
        val options = arrayOf("删除", if (!message.unread) "标记未读" else "标记已读")
        AlertDialog.Builder(requireContext())
            .setTitle(message.sender)
            .setItems(options) { _, which ->
                when (which) {
                    0 -> { // 删除
                        messageList.removeAll { it.id == message.id }
                        messageAdapter.updateMessages(messageList)
                        Toast.makeText(context, "已删除", Toast.LENGTH_SHORT).show()
                    }
                    1 -> { // 标记未读/已读
                        val idx = messageList.indexOfFirst { it.id == message.id }
                        if (idx != -1) {
                            messageList[idx] = messageList[idx].copy(unread = !message.unread)
                            messageAdapter.updateMessages(messageList)
                            Toast.makeText(context, if (!message.unread) "已标记未读" else "已标记已读", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 