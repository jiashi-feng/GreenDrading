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

/**
 * 商品咨询邀请适配器
 * 用于在RecyclerView中显示商品咨询邀请列表，包含用户信息和商品信息
 *
 * @property invitations 邀请列表数据
 * @property onReplyClick 回复按钮点击回调
 * @property onCloseClick 关闭按钮点击回调
 */
class InvitationAdapter(
    private var invitations: List<InvitationItem>,
    private val onReplyClick: (InvitationItem) -> Unit,
    private val onCloseClick: (InvitationItem) -> Unit
) : RecyclerView.Adapter<InvitationAdapter.ViewHolder>() {

    /**
     * ViewHolder类
     * 持有邀请列表项中的各个视图引用
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userAvatar: ShapeableImageView = view.findViewById(R.id.iv_user_avatar)      // 用户头像
        val userName: TextView = view.findViewById(R.id.tv_user_name)                    // 用户名称
        val questionContent: TextView = view.findViewById(R.id.tv_question_content)      // 问题内容
        val productImage: ImageView = view.findViewById(R.id.iv_product_image)           // 商品图片
        val productName: TextView = view.findViewById(R.id.tv_product_name)              // 商品名称
        val productPrice: TextView = view.findViewById(R.id.tv_product_price)            // 商品价格
        val timeAndReplies: TextView = view.findViewById(R.id.tv_time_and_replies)       // 时间和回复数
        val replyButton: Button = view.findViewById(R.id.btn_reply)                      // 回复按钮
        val closeButton: ImageView = view.findViewById(R.id.iv_close_button)             // 关闭按钮
    }

    /**
     * 创建ViewHolder
     * 加载邀请列表项的布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_invitation, parent, false)
        return ViewHolder(view)
    }

    /**
     * 绑定ViewHolder
     * 设置邀请列表项的数据和点击事件
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val invitation = invitations[position]
        // 设置用户信息
        holder.userAvatar.setImageResource(invitation.userAvatarRes)
        holder.userName.text = invitation.userName
        holder.questionContent.text = invitation.question
        
        // 设置商品信息
        holder.productImage.setImageResource(invitation.productImgRes)
        holder.productName.text = invitation.productName
        holder.productPrice.text = invitation.productPrice
        holder.timeAndReplies.text = invitation.timeAndReplies

        // 设置按钮点击事件
        holder.replyButton.setOnClickListener { onReplyClick(invitation) }
        holder.closeButton.setOnClickListener { onCloseClick(invitation) }
    }

    /**
     * 获取邀请列表项数量
     */
    override fun getItemCount() = invitations.size

    /**
     * 更新邀请列表数据
     * @param newInvitations 新的邀请列表
     */
    fun updateInvitations(newInvitations: List<InvitationItem>) {
        invitations = newInvitations
        notifyDataSetChanged()
    }
} 