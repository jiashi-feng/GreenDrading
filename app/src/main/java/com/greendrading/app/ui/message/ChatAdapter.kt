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

class ChatAdapter(
    private val messages: MutableList<ChatMessage>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_SENT_TEXT = 1
    private val VIEW_TYPE_RECEIVED_TEXT = 2
    private val VIEW_TYPE_SENT_IMAGE = 3
    private val VIEW_TYPE_RECEIVED_IMAGE = 4

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.isSentByUser) {
            if (message.imageRes != null) VIEW_TYPE_SENT_IMAGE else VIEW_TYPE_SENT_TEXT
        } else {
            if (message.imageRes != null) VIEW_TYPE_RECEIVED_IMAGE else VIEW_TYPE_RECEIVED_TEXT
        }
    }

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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder.itemViewType) {
            VIEW_TYPE_SENT_TEXT -> (holder as SentMessageViewHolder).bind(message)
            VIEW_TYPE_RECEIVED_TEXT -> (holder as ReceivedMessageViewHolder).bind(message)
            VIEW_TYPE_SENT_IMAGE -> (holder as SentImageMessageViewHolder).bind(message)
            VIEW_TYPE_RECEIVED_IMAGE -> (holder as ReceivedImageMessageViewHolder).bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    fun addMessage(message: ChatMessage) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    fun updateMessages(newMessages: List<ChatMessage>) {
        messages.clear()
        messages.addAll(newMessages)
        notifyDataSetChanged()
    }

    class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageContent: TextView = itemView.findViewById(R.id.tv_message_content)
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)

        fun bind(message: ChatMessage) {
            messageContent.text = message.content
            avatar.setImageResource(message.avatarRes)
        }
    }

    class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageContent: TextView = itemView.findViewById(R.id.tv_message_content)
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)

        fun bind(message: ChatMessage) {
            messageContent.text = message.content
            avatar.setImageResource(message.avatarRes)
        }
    }

    class SentImageMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)
        private val chatImage: ImageView = itemView.findViewById(R.id.iv_chat_image)

        fun bind(message: ChatMessage) {
            avatar.setImageResource(message.avatarRes)
            message.imageRes?.let {
                Glide.with(itemView.context).load(it).into(chatImage)
            }
        }
    }

    class ReceivedImageMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: CircleImageView = itemView.findViewById(R.id.iv_avatar)
        private val chatImage: ImageView = itemView.findViewById(R.id.iv_chat_image)

        fun bind(message: ChatMessage) {
            avatar.setImageResource(message.avatarRes)
            message.imageRes?.let {
                Glide.with(itemView.context).load(it).into(chatImage)
            }
        }
    }
} 