package com.greendrading.app.ui.dailyshopping

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

class CouponAdapter(private var coupons: List<Coupon>, private val onItemClick: (Coupon) -> Unit) : RecyclerView.Adapter<CouponAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val couponImage: ImageView = view.findViewById(R.id.iv_coupon_image)
        val couponCategory: TextView = view.findViewById(R.id.tv_coupon_category)
        val couponValue: TextView = view.findViewById(R.id.tv_coupon_value)
        val couponCondition: TextView = view.findViewById(R.id.tv_coupon_condition)
        val timeLeft: TextView = view.findViewById(R.id.tv_time_left)
        val remindMeButton: Button = view.findViewById(R.id.btn_remind_me)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coupon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coupon = coupons[position]
        holder.couponImage.setImageResource(coupon.imageRes)
        holder.couponCategory.text = coupon.category
        holder.couponValue.text = coupon.value
        holder.couponCondition.text = coupon.condition
        holder.timeLeft.text = coupon.timeLeft

        holder.remindMeButton.setOnClickListener {
            Toast.makeText(holder.itemView.context, "提醒成功！", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.setOnClickListener {
            onItemClick(coupon)
        }
    }

    override fun getItemCount(): Int = coupons.size

    fun updateCoupons(newCoupons: List<Coupon>) {
        coupons = newCoupons
        notifyDataSetChanged()
    }
} 