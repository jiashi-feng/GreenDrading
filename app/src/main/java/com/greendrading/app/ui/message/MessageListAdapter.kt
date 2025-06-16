package com.greendrading.app.ui.message

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R
import com.google.android.material.imageview.ShapeableImageView

class MessageListAdapter(
    private var messages: List<Message>,
    private val onItemClick: (Message) -> Unit,
    private val onActionClick: (Message) -> Unit,
    private val onItemLongClick: (Message) -> Unit
) : RecyclerView.Adapter<MessageListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val typeIcon: ShapeableImageView = view.findViewById(R.id.iv_type_icon)
        val avatar: ShapeableImageView = view.findViewById(R.id.iv_avatar)
        val sender: TextView = view.findViewById(R.id.tv_sender)
        val content: TextView = view.findViewById(R.id.tv_content)
        val time: TextView = view.findViewById(R.id.tv_time)
        val unreadDot: View = view.findViewById(R.id.v_unread)
        val actionBtn: Button = view.findViewById(R.id.btn_action)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messages[position]
        // Add log for debugging
        Log.d("MessageAdapter", "Binding message: ${message.sender}, Content: ${message.content}, JumpType: ${message.jumpType}, AvatarRes: ${message.avatarRes}, TypeIconRes: ${getTypeIconRes(message)}")

        // 根据消息类型决定显示头像还是类型icon，并设置背景色
        when (message.jumpType) {
            MessageJumpType.LOGISTICS -> {
                Log.d("MessageAdapter", "LOGISTICS: Showing typeIcon, hiding avatar")
                holder.avatar.visibility = View.GONE
                holder.typeIcon.visibility = View.VISIBLE
                holder.typeIcon.setImageResource(getTypeIconRes(message))
                holder.typeIcon.backgroundTintList = holder.itemView.context.resources.getColorStateList(R.color.message_type_green, null)
            }
            MessageJumpType.COUPON -> {
                Log.d("MessageAdapter", "COUPON: Showing typeIcon, hiding avatar")
                holder.avatar.visibility = View.GONE
                holder.typeIcon.visibility = View.VISIBLE
                holder.typeIcon.setImageResource(getTypeIconRes(message))
                holder.typeIcon.backgroundTintList = holder.itemView.context.resources.getColorStateList(R.color.message_type_blue, null)
            }
            else -> { // 对于 CHAT, DISCUSS 以及其他普通消息，显示头像
                Log.d("MessageAdapter", "OTHER: Showing avatar, hiding typeIcon")
                holder.avatar.visibility = View.VISIBLE
                holder.typeIcon.visibility = View.GONE
                holder.avatar.setImageResource(message.avatarRes)
            }
        }

        // 其他信息
        holder.sender.text = message.sender
        holder.content.text = message.content
        holder.time.text = message.time
        holder.unreadDot.visibility = if (message.unread) View.VISIBLE else View.GONE
        // 操作按钮
        if (message.jumpType == MessageJumpType.COUPON) {
            holder.actionBtn.visibility = View.VISIBLE
            holder.actionBtn.text = "去领取"
        } else { // 隐藏其他所有类型的操作按钮，包括LOGISTICS
            holder.actionBtn.visibility = View.GONE
        }
        holder.actionBtn.setOnClickListener { onActionClick(message) }
        // 点击整卡
        holder.itemView.setOnClickListener { onItemClick(message) }
        holder.itemView.setOnLongClickListener {
            onItemLongClick(message)
            true
        }
    }

    override fun getItemCount() = messages.size

    fun updateMessages(newMessages: List<Message>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    private fun getTypeIconRes(message: Message): Int {
        return when (message.jumpType) {
            MessageJumpType.LOGISTICS -> R.drawable.ic_message_type_logistics
            MessageJumpType.CHAT -> R.drawable.ic_message_type_chat
            MessageJumpType.DISCUSS -> R.drawable.ic_message_type_discuss
            MessageJumpType.COUPON -> R.drawable.ic_message_type_coupon
            else -> R.drawable.ic_message_type_default
        }
    }
} 