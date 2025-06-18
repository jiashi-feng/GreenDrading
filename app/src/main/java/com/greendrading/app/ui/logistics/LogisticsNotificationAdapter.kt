package com.greendrading.app.ui.logistics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

class LogisticsNotificationAdapter(
    private var notifications: List<LogisticsNotification>,
    private val onItemClick: (LogisticsNotification) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_SIGNED = 1
    private val VIEW_TYPE_SHIPPING = 2
    private val VIEW_TYPE_PICKUP = 3
    private val VIEW_TYPE_ORDER_STATUS = 4

    override fun getItemViewType(position: Int): Int {
        return when (notifications[position]) {
            is LogisticsNotification.SignedNotification -> VIEW_TYPE_SIGNED
            is LogisticsNotification.ShippingNotification -> VIEW_TYPE_SHIPPING
            is LogisticsNotification.PickupReminderNotification -> VIEW_TYPE_PICKUP
            is LogisticsNotification.OrderStatusUpdateNotification -> VIEW_TYPE_ORDER_STATUS
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SIGNED -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_logistics_notification_signed, parent, false)
                SignedViewHolder(view)
            }
            VIEW_TYPE_SHIPPING -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_logistics_notification_shipping, parent, false)
                ShippingViewHolder(view)
            }
            VIEW_TYPE_PICKUP -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_logistics_notification_pickup, parent, false)
                PickupViewHolder(view)
            }
            VIEW_TYPE_ORDER_STATUS -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_logistics_notification_order_status, parent, false)
                OrderStatusUpdateViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_SIGNED -> {
                val signedHolder = holder as SignedViewHolder
                val notification = notifications[position] as LogisticsNotification.SignedNotification
                signedHolder.bind(notification, onItemClick)
            }
            VIEW_TYPE_SHIPPING -> {
                val shippingHolder = holder as ShippingViewHolder
                val notification = notifications[position] as LogisticsNotification.ShippingNotification
                shippingHolder.bind(notification, onItemClick)
            }
            VIEW_TYPE_PICKUP -> {
                val pickupHolder = holder as PickupViewHolder
                val notification = notifications[position] as LogisticsNotification.PickupReminderNotification
                pickupHolder.bind(notification, onItemClick)
            }
            VIEW_TYPE_ORDER_STATUS -> {
                val orderStatusHolder = holder as OrderStatusUpdateViewHolder
                val notification = notifications[position] as LogisticsNotification.OrderStatusUpdateNotification
                orderStatusHolder.bind(notification, onItemClick)
            }
        }
    }

    override fun getItemCount(): Int = notifications.size

    fun updateNotifications(newNotifications: List<LogisticsNotification>) {
        notifications = newNotifications
        notifyDataSetChanged()
    }

    inner class SignedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_notification_title)
        private val date: TextView = itemView.findViewById(R.id.tv_notification_date)
        private val message: TextView = itemView.findViewById(R.id.tv_notification_message)
        private val productImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val viewLogisticsButton: Button = itemView.findViewById(R.id.btn_view_logistics)
        private val moreOptions: TextView = itemView.findViewById(R.id.tv_more_options)
        private val subNotificationsLayout: LinearLayout = itemView.findViewById(R.id.ll_sub_notifications)

        init {
            moreOptions.setOnClickListener {
                val notification = notifications[adapterPosition] as LogisticsNotification.SignedNotification
                notification.isExpanded = !notification.isExpanded
                notifyItemChanged(adapterPosition)
            }
        }

        fun bind(notification: LogisticsNotification.SignedNotification, onItemClick: (LogisticsNotification) -> Unit) {
            title.text = "订单签收通知"
            date.text = notification.date
            message.text = notification.message
            productImage.setImageResource(notification.imageUrl)
            viewLogisticsButton.text = notification.viewLogisticsButtonText
            viewLogisticsButton.setOnClickListener { 
                Toast.makeText(itemView.context, "点击了${notification.viewLogisticsButtonText}", Toast.LENGTH_SHORT).show()
            }
            subNotificationsLayout.visibility = if (notification.isExpanded) View.VISIBLE else View.GONE
            moreOptions.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, if (notification.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down, 0)
            itemView.setOnClickListener { onItemClick(notification) }
        }
    }

    inner class ShippingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_notification_title)
        private val date: TextView = itemView.findViewById(R.id.tv_notification_date)
        private val mainMessage: TextView = itemView.findViewById(R.id.tv_notification_message)
        private val productImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val viewProgressButton: Button = itemView.findViewById(R.id.btn_view_progress)
        private val moreOptions: TextView = itemView.findViewById(R.id.tv_more_options)
        private val subNotificationsLayout: LinearLayout = itemView.findViewById(R.id.ll_sub_notifications)

        init {
            moreOptions.setOnClickListener {
                val notification = notifications[adapterPosition] as LogisticsNotification.ShippingNotification
                notification.isExpanded = !notification.isExpanded
                notifyItemChanged(adapterPosition)
                Toast.makeText(itemView.context, "更多消息点击", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(notification: LogisticsNotification.ShippingNotification, onItemClick: (LogisticsNotification) -> Unit) {
            title.text = "商品发货通知"
            date.text = notification.date
            mainMessage.text = notification.mainMessage
            productImage.setImageResource(notification.imageUrl)
            viewProgressButton.text = notification.viewProgressButtonText
            viewProgressButton.setOnClickListener {
                Toast.makeText(itemView.context, "点击了${notification.viewProgressButtonText}", Toast.LENGTH_SHORT).show()
            }
            subNotificationsLayout.visibility = if (notification.isExpanded) View.VISIBLE else View.GONE
            moreOptions.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, if (notification.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down, 0)
            itemView.setOnClickListener { onItemClick(notification) }
        }
    }

    inner class PickupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_notification_title)
        private val date: TextView = itemView.findViewById(R.id.tv_notification_date)
        private val message: TextView = itemView.findViewById(R.id.tv_notification_message)
        private val productImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val viewPickupButton: Button = itemView.findViewById(R.id.btn_view_pickup_info)
        private val moreOptions: TextView = itemView.findViewById(R.id.tv_more_options)
        private val subNotificationsLayout: LinearLayout = itemView.findViewById(R.id.ll_sub_notifications)

        init {
            moreOptions.setOnClickListener {
                val notification = notifications[adapterPosition] as LogisticsNotification.PickupReminderNotification
                notification.isExpanded = !notification.isExpanded
                notifyItemChanged(adapterPosition)
                Toast.makeText(itemView.context, "更多消息点击", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(notification: LogisticsNotification.PickupReminderNotification, onItemClick: (LogisticsNotification) -> Unit) {
            title.text = "商品待取件提醒"
            date.text = notification.date
            message.text = notification.message
            productImage.setImageResource(notification.imageUrl)
            viewPickupButton.text = notification.viewPickupButtonText
            viewPickupButton.setOnClickListener {
                Toast.makeText(itemView.context, "点击了${notification.viewPickupButtonText}", Toast.LENGTH_SHORT).show()
            }
            subNotificationsLayout.visibility = if (notification.isExpanded) View.VISIBLE else View.GONE
            moreOptions.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, if (notification.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down, 0)
            itemView.setOnClickListener { onItemClick(notification) }
        }
    }

    inner class OrderStatusUpdateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_notification_title)
        private val date: TextView = itemView.findViewById(R.id.tv_notification_date)
        private val mainMessage: TextView = itemView.findViewById(R.id.tv_notification_message)
        private val productImage: ImageView = itemView.findViewById(R.id.iv_product_image)
        private val viewProgressButton: Button = itemView.findViewById(R.id.btn_view_progress)
        private val moreOptions: TextView = itemView.findViewById(R.id.tv_more_options)
        private val subNotificationsLayout: LinearLayout = itemView.findViewById(R.id.ll_sub_notifications)

        init {
            moreOptions.setOnClickListener {
                val notification = notifications[adapterPosition] as LogisticsNotification.OrderStatusUpdateNotification
                notification.isExpanded = !notification.isExpanded
                notifyItemChanged(adapterPosition)
                Toast.makeText(itemView.context, "更多消息点击", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(notification: LogisticsNotification.OrderStatusUpdateNotification, onItemClick: (LogisticsNotification) -> Unit) {
            title.text = "订单状态更新"
            date.text = notification.date
            mainMessage.text = notification.mainMessage
            productImage.setImageResource(notification.imageUrl)
            viewProgressButton.text = notification.viewProgressButtonText
            viewProgressButton.setOnClickListener {
                Toast.makeText(itemView.context, "点击了${notification.viewProgressButtonText}", Toast.LENGTH_SHORT).show()
            }
            subNotificationsLayout.visibility = if (notification.isExpanded) View.VISIBLE else View.GONE
            moreOptions.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, if (notification.isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_arrow_down, 0)
            itemView.setOnClickListener { onItemClick(notification) }
        }
    }
} 