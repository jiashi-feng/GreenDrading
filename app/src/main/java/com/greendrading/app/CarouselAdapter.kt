package com.greendrading.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

/**
 * 轮播图适配器
 * 异常处理说明：
 * 1. 视图相关异常：
 *    - IllegalStateException: 当视图创建失败时
 *    - NullPointerException: 当视图ID未找到时
 * 2. 资源相关异常：
 *    - Resources.NotFoundException: 当图片资源未找到时
 * 3. 索引相关异常：
 *    - IndexOutOfBoundsException: 当访问图片列表越界时
 * 建议的异常处理方式：
 * - 使用安全调用操作符（?.）和空值合并操作符（?:）
 * - 在关键操作处添加try-catch块
 * - 实现适当的错误恢复机制
 * - 记录异常日志以便调试
 */
class CarouselAdapter(private val images: List<Int>) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {

    class CarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // 注意：此处可能抛出NullPointerException
        val imageView: ImageView = view.findViewById(R.id.carouselImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        try {
            // 注意：此处可能抛出IllegalStateException
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carousel, parent, false)
            return CarouselViewHolder(view)
        } catch (e: Exception) {
            // 处理视图创建异常
            throw IllegalStateException("Failed to create carousel view holder", e)
        }
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        try {
            // 注意：此处可能抛出Resources.NotFoundException和IndexOutOfBoundsException
            holder.imageView.setImageResource(images[position])
        } catch (e: Exception) {
            // 处理图片加载异常
        }
    }
    

    override fun getItemCount(): Int {
        return images.size
    }
} 