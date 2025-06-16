package com.greendrading.app.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

class RecommendAdapter(
    private var products: List<RecommendProduct>,
    private val onItemClick: (RecommendProduct) -> Unit
) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.iv_product)
        val title: TextView = view.findViewById(R.id.tv_title)
        val price: TextView = view.findViewById(R.id.tv_price)
        val userCount: TextView = view.findViewById(R.id.tv_user_count)
        val location: TextView = view.findViewById(R.id.tv_location)
        val tag: TextView = view.findViewById(R.id.tv_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommend, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.image.setImageResource(product.imageRes)
        holder.title.text = product.title
        holder.price.text = product.price
        holder.userCount.text = product.userCount
        holder.location.text = product.location
        holder.tag.text = product.tag
        holder.itemView.setOnClickListener { onItemClick(product) }
    }

    override fun getItemCount() = products.size

    fun updateProducts(newProducts: List<RecommendProduct>) {
        products = newProducts
        notifyDataSetChanged()
    }
} 