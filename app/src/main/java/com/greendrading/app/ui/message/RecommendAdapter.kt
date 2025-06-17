package com.greendrading.app.ui.message

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

/**
 * 推荐商品适配器
 * 用于在RecyclerView中显示推荐商品列表，包含商品图片、标题、价格等信息
 *
 * @property products 推荐商品列表数据
 * @property onItemClick 商品项点击回调
 */
class RecommendAdapter(
    private var products: List<RecommendProduct>,
    private val onItemClick: (RecommendProduct) -> Unit
) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    /**
     * ViewHolder类
     * 持有推荐商品列表项中的各个视图引用
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.iv_product)        // 商品图片
        val title: TextView = view.findViewById(R.id.tv_title)          // 商品标题
        val price: TextView = view.findViewById(R.id.tv_price)          // 商品价格
        val userCount: TextView = view.findViewById(R.id.tv_user_count) // 用户数量
        val location: TextView = view.findViewById(R.id.tv_location)    // 商品位置
        val tag: TextView = view.findViewById(R.id.tv_tag)              // 商品标签
    }

    /**
     * 创建ViewHolder
     * 加载推荐商品列表项的布局
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommend, parent, false)
        return ViewHolder(view)
    }

    /**
     * 绑定ViewHolder
     * 设置推荐商品列表项的数据和点击事件
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        // 设置商品信息
        holder.image.setImageResource(product.imageRes)
        holder.title.text = product.title
        holder.price.text = product.price
        holder.userCount.text = product.userCount
        holder.location.text = product.location
        holder.tag.text = product.tag
        // 设置点击事件
        holder.itemView.setOnClickListener { onItemClick(product) }
    }

    /**
     * 获取推荐商品列表项数量
     */
    override fun getItemCount() = products.size

    /**
     * 更新推荐商品列表数据
     * @param newProducts 新的推荐商品列表
     */
    fun updateProducts(newProducts: List<RecommendProduct>) {
        products = newProducts
        notifyDataSetChanged()
    }
} 