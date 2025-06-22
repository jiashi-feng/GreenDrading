package com.greendrading.app.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

/**
 * 聊天消息适配器
 * 用于在RecyclerView中显示聊天消息列表，支持发送和接收的文本消息和图片消息
 *
 * @property messages 聊天消息列表
 */
class ChatAdapter(
    private val messages: MutableList<ChatMessage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 视图类型常量定义
    private val VIEW_TYPE_SENT_TEXT = 1      // 发送的文本消息
    private val VIEW_TYPE_RECEIVED_TEXT = 2  // 接收的文本消息
    private val VIEW_TYPE_SENT_IMAGE = 3     // 发送的图片消息
    private val VIEW_TYPE_RECEIVED_IMAGE = 4 // 接收的图片消息

    /**
     * 获取消息视图类型
     * 根据消息是否由用户发送以及是否包含图片来决定视图类型
     */
    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.isSentByUser) {
            if (message.imageRes != null) VIEW_TYPE_SENT_IMAGE else VIEW_TYPE_SENT_TEXT
        } else {
            if (message.imageRes != null) VIEW_TYPE_RECEIVED_IMAGE else VIEW_TYPE_RECEIVED_TEXT
        }
    }

    /**
     * 创建ViewHolder
     * 根据视图类型创建对应的ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SENT_TEXT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_sent, parent, false)
                SentMessageViewHolder(view)
            }
            VIEW_TYPE_RECEIVED_TEXT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_received, parent, false)
                ReceivedMessageViewHolder(view)
            }
            VIEW_TYPE_SENT_IMAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_image_sent, parent, false)
                SentImageMessageViewHolder(view)
            }
            VIEW_TYPE_RECEIVED_IMAGE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_chat_image_received, parent, false)
                ReceivedImageMessageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    /**
     * 绑定ViewHolder
     * 根据ViewHolder类型绑定对应的消息数据
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder.itemViewType) {
            VIEW_TYPE_SENT_TEXT -> (holder as SentMessageViewHolder).bind(message)
            VIEW_TYPE_RECEIVED_TEXT -> (holder as ReceivedMessageViewHolder).bind(message)
            VIEW_TYPE_SENT_IMAGE -> (holder as SentImageMessageViewHolder).bind(message)
            VIEW_TYPE_RECEIVED_IMAGE -> (holder as ReceivedImageMessageViewHolder).bind(message)
        }
    }

    /**
     * 获取消息列表项数量
     */
    override fun getItemCount(): Int = messages.size

    /**
     * 添加新消息
     * @param message 要添加的聊天消息
     */
    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    /**
     * 更新消息列表
     * @param newMessages 新的消息列表
     */
    fun updateMessages(newMessages: List<ChatMessage>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }

    /**
     * 发送的文本消息ViewHolder
     */
    class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageContent: TextView = itemView.findViewById(R.id.tv_message_content)  // 消息内容
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)            // 发送者头像

        fun bind(message: ChatMessage) {
            messageContent.text = message.content
            avatar.setImageResource(message.avatarRes)
        }
    }

    /**
     * 接收的文本消息ViewHolder
     */
    class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageContent: TextView = itemView.findViewById(R.id.tv_message_content)  // 消息内容
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)            // 发送者头像

        fun bind(message: ChatMessage) {
            messageContent.text = message.content
            avatar.setImageResource(message.avatarRes)
        }
    }

    /**
     * 发送的图片消息ViewHolder
     */
    class SentImageMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)            // 发送者头像
        private val chatImage: ImageView = itemView.findViewById(R.id.iv_chat_image)           // 聊天图片

        fun bind(message: ChatMessage) {
            avatar.setImageResource(message.avatarRes)
            message.imageRes?.let {
                Glide.with(itemView.context).load(it).into(chatImage)
            }
        }
    }

    /**
     * 接收的图片消息ViewHolder
     */
    class ReceivedImageMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)            // 发送者头像
        private val chatImage: ImageView = itemView.findViewById(R.id.iv_chat_image)           // 聊天图片

        fun bind(message: ChatMessage) {
            avatar.setImageResource(message.avatarRes)
            message.imageRes?.let {
                Glide.with(itemView.context).load(it).into(chatImage)
            }
        }
    }
} 