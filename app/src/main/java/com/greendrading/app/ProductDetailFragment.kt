package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.greendrading.app.databinding.FragmentProductDetailBinding
import com.google.android.material.button.MaterialButton
import com.greendrading.app.adapter.ImageCarouselAdapter
import android.os.Handler
import android.os.Looper

/**
 * 商品详情Fragment
 * 异常处理说明：
 * 1. 视图绑定相关异常：
 *    - IllegalStateException: 当视图绑定为空时访问binding属性
 *    - NullPointerException: 当视图ID未找到时
 * 2. 导航相关异常：
 *    - IllegalStateException: 当导航操作无效时
 *    - IllegalArgumentException: 当导航参数无效时
 * 3. 轮播图相关异常：
 *    - IndexOutOfBoundsException: 当轮播图索引越界时
 *    - IllegalStateException: 当ViewPager2状态异常时
 * 4. 资源相关异常：
 *    - Resources.NotFoundException: 当图片资源未找到时
 * 5. 生命周期相关异常：
 *    - IllegalStateException: 当在Fragment销毁后访问视图时
 * 建议的异常处理方式：
 * - 使用安全调用操作符（?.）和空值合并操作符（?:）
 * - 在关键操作处添加try-catch块
 * - 实现适当的错误恢复机制
 * - 记录异常日志以便调试
 */
class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()

    private val handler = Handler(Looper.getMainLooper())
    private val carouselRunnable = object : Runnable {
        override fun run() {
            try {
                if (binding.productImageCarousel.adapter != null && binding.productImageCarousel.adapter!!.itemCount > 0) {
                    val currentItem = binding.productImageCarousel.currentItem
                    val nextItem = (currentItem + 1) % binding.productImageCarousel.adapter!!.itemCount
                    binding.productImageCarousel.setCurrentItem(nextItem, true)
                }
                handler.postDelayed(this, 3000) // Scroll every 3 seconds
            } catch (e: Exception) {
                // 处理轮播图异常
                handler.removeCallbacks(this)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 注意：此处可能抛出IllegalStateException
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            setupUI()
            setupImageCarousel()
        } catch (e: Exception) {
            // 处理UI初始化异常
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            handler.postDelayed(carouselRunnable, 3000)
        } catch (e: Exception) {
            // 处理轮播图启动异常
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            handler.removeCallbacks(carouselRunnable)
        } catch (e: Exception) {
            // 处理轮播图停止异常
        }
    }

    private fun setupUI() {
        try {
            // All products will now display as "多肉产品详情介绍"
            binding.productTitle.text = "多肉产品详情介绍"
            binding.productPrice.text = "¥40"
            binding.productDescription.text = """这是一款精选的多肉植物，包含多种类型，形态各异，色彩斑斓。它们耐旱易养护，是美化家居、办公环境的理想选择。无论是园艺新手还是资深爱好者，都能从中找到养护的乐趣。多肉植物具有独特的观赏价值，能够净化空气，舒缓心情。请根据光照、浇水、温度、施肥、修剪和病虫害防治等养护技巧进行照料，确保其健康成长。"""

            binding.addToCartButton.setOnClickListener {
                Toast.makeText(requireContext(), "添加成功", Toast.LENGTH_SHORT).show()
            }

            binding.backButton.setOnClickListener {
                // 跳转到首页
                findNavController().navigate(R.id.navigation_home)
            }

            binding.buyButton.setOnClickListener {
                findNavController().navigate(R.id.action_productDetailFragment_to_confirmPaymentFragment)
            }
        } catch (e: Exception) {
            // 处理UI设置异常
        }
    }

    private fun setupImageCarousel() {
        try {
            val images = listOf(
                R.drawable.duorou1,
                R.drawable.duorou2,
                R.drawable.duorou4
            )
            val adapter = ImageCarouselAdapter(images)
            binding.productImageCarousel.adapter = adapter
        } catch (e: Exception) {
            // 处理轮播图设置异常
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            _binding = null
        } catch (e: Exception) {
            // 处理资源清理异常
        }
    }
} 