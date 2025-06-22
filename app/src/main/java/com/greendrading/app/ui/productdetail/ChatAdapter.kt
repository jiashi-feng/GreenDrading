package com.greendrading.app.ui.productdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R
import com.google.android.material.imageview.ShapeableImageView

class ChatAdapter(private var messages: List<ChatMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_MESSAGE_SENT = 1
    private val VIEW_TYPE_MESSAGE_RECEIVED = 2

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.isSelf) VIEW_TYPE_MESSAGE_SENT else VIEW_TYPE_MESSAGE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message_sent, parent, false)
            SentMessageViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message_received, parent, false)
            ReceivedMessageViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (holder.itemViewType == VIEW_TYPE_MESSAGE_SENT) {
            (holder as SentMessageViewHolder).bind(message)
        } else {
            (holder as ReceivedMessageViewHolder).bind(message)
        }
    }

    override fun getItemCount(): Int = messages.size

    fun updateMessages(newMessages: List<ChatMessage>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    inner class SentMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageContent: TextView = itemView.findViewById(R.id.tv_message_content)

        fun bind(message: ChatMessage) {
            messageContent.text = message.content
        }
    }

    inner class ReceivedMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatar: ShapeableImageView = itemView.findViewById(R.id.iv_avatar)
        private val messageContent: TextView = itemView.findViewById(R.id.tv_message_content)

        fun bind(message: ChatMessage) {
            // You might want to set different avatars based on senderId or a default one
            if (message.avatarRes != 0) {
                avatar.setImageResource(message.avatarRes)
            } else {
                avatar.setImageResource(R.drawable.default_avatar)
            }
            messageContent.text = message.content
        }
    }
} 