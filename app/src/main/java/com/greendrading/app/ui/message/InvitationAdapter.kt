package com.greendrading.app.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.greendrading.app.R

class InvitationAdapter(
    private var invitations: List<InvitationItem>,
    private val onReplyClick: (InvitationItem) -> Unit,
    private val onCloseClick: (InvitationItem) -> Unit
) : RecyclerView.Adapter<InvitationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userAvatar: ShapeableImageView = view.findViewById(R.id.iv_user_avatar)
        val userName: TextView = view.findViewById(R.id.tv_user_name)
        val questionContent: TextView = view.findViewById(R.id.tv_question_content)
        val productImage: ImageView = view.findViewById(R.id.iv_product_image)
        val productName: TextView = view.findViewById(R.id.tv_product_name)
        val productPrice: TextView = view.findViewById(R.id.tv_product_price)
        val timeAndReplies: TextView = view.findViewById(R.id.tv_time_and_replies)
        val replyButton: Button = view.findViewById(R.id.btn_reply)
        val closeButton: ImageView = view.findViewById(R.id.iv_close_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_invitation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val invitation = invitations[position]
        holder.userAvatar.setImageResource(invitation.userAvatarRes)
        holder.userName.text = invitation.userName
        holder.questionContent.text = invitation.question
        holder.productImage.setImageResource(invitation.productImgRes)
        holder.productName.text = invitation.productName
        holder.productPrice.text = invitation.productPrice
        holder.timeAndReplies.text = invitation.timeAndReplies

        holder.replyButton.setOnClickListener { onReplyClick(invitation) }
        holder.closeButton.setOnClickListener { onCloseClick(invitation) }
    }

    override fun getItemCount() = invitations.size

    fun updateInvitations(newInvitations: List<InvitationItem>) {
        invitations = newInvitations
        notifyDataSetChanged()
    }
} 