package com.greendrading.app.ui.productdetail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private val chatMessages = mutableListOf<ChatMessage>()
    private var isFavorited = false // State for the favorite icon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // Initialize RecyclerView
        chatRecyclerView = findViewById(R.id.rv_chat_messages)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(chatMessages)
        chatRecyclerView.adapter = chatAdapter

        // Load mock chat data
        loadChatMessages()

        // Set up top bar interactions
        findViewById<ImageView>(R.id.btn_back).setOnClickListener { finish() }

        val btnAddOptions = findViewById<ImageView>(R.id.btn_add_options)
        btnAddOptions.visibility = View.VISIBLE // Make it visible as per the image
        btnAddOptions.setOnClickListener { view ->
            showPopupMenu(view)
        }

        val btnFavorite = findViewById<ImageView>(R.id.btn_favorite)
        btnFavorite.setOnClickListener { 
            isFavorited = !isFavorited
            if (isFavorited) {
                btnFavorite.setImageResource(R.drawable.ic_star_filled)
                Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show()
            } else {
                btnFavorite.setImageResource(R.drawable.ic_star_unfilled)
                Toast.makeText(this, "已取消收藏", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadChatMessages() {
        // Add mock messages
        chatMessages.add(ChatMessage("seller1", "宝，欢迎光临小店，本店出售多种植物花卉，均自家培育，非常健康", false, R.drawable.avatar_shop))
        chatMessages.add(ChatMessage("user1", "请问都有什么花卉啊？", true, R.drawable.avatar_user))
        chatMessages.add(ChatMessage("seller1", "花卉有海棠花，玫瑰花，栀子花等。如果需要绿植，我们还有文竹等。", false, R.drawable.avatar_shop))
        chatMessages.add(ChatMessage("user1", "哪种花香味比较浓郁一些？", true, R.drawable.avatar_user))
        chatMessages.add(ChatMessage("seller1", "茉莉花，栀子花的香味都很浓郁。茉莉花比较清雅，而栀子花香比较香甜", false, R.drawable.avatar_shop))
        chatAdapter.notifyDataSetChanged()
        chatRecyclerView.scrollToPosition(chatMessages.size - 1)
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.product_detail_options_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_buy_again -> {
                    // Handle "再次购买"
                    true
                }
                R.id.action_request_refund -> {
                    // Handle "我要退款"
                    true
                }
                R.id.action_extend_receipt -> {
                    // Handle "延长收货"
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
} 