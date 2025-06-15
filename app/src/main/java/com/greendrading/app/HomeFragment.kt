package com.greendrading.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.databinding.FragmentHomeBinding

/**
 * 首页Fragment
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
 * 4. 生命周期相关异常：
 *    - IllegalStateException: 当在Fragment销毁后访问视图时
 * 建议的异常处理方式：
 * - 使用安全调用操作符（?.）和空值合并操作符（?:）
 * - 在关键操作处添加try-catch块
 * - 实现适当的错误恢复机制
 * - 记录异常日志以便调试
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val carouselImages = listOf(
        R.drawable.sell_plant_10,
        R.drawable.sell_plant_11,
        R.drawable.sell_plant_1,
        R.drawable.sell_plant_2
    )

    private val autoScrollHandler = Handler(Looper.getMainLooper())
    private val autoScrollRunnable = object : Runnable {
        override fun run() {
            try {
                val currentItem = binding.carouselViewPager.currentItem
                val nextItem = if (currentItem == carouselImages.size - 1) 0 else currentItem + 1
                binding.carouselViewPager.currentItem = nextItem
                autoScrollHandler.postDelayed(this, 3000) // 每3秒滑动一次
            } catch (e: Exception) {
                // 处理轮播图异常
                autoScrollHandler.removeCallbacks(this)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 注意：此处可能抛出IllegalStateException
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCarousel()
    }

    private fun setupClickListeners() {
        try {
            // 设置搜索按钮点击事件
            binding.searchButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }

            // 设置更多分类按钮点击事件
            binding.btnMoreCategory.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_homeCategoryFragment)
            }

            // 设置养护技巧按钮点击事件
            binding.btnCareTips.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_careTipsFragment)
            }

            // 设置商品项点击事件
            binding.item1.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToProductDetailFragment("product_1")
                )
            }
        } catch (e: Exception) {
            // 处理点击事件设置异常
        }
    }

    private fun setupCarousel() {
        try {
            val adapter = CarouselAdapter(carouselImages)
            binding.carouselViewPager.adapter = adapter
            // 启动自动轮播
            autoScrollHandler.postDelayed(autoScrollRunnable, 3000)
        } catch (e: Exception) {
            // 处理轮播图设置异常
        }
    }

    override fun onPause() {
        super.onPause()
        try {
            autoScrollHandler.removeCallbacks(autoScrollRunnable) // 页面不可见时停止轮播
        } catch (e: Exception) {
            // 处理轮播图停止异常
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            autoScrollHandler.postDelayed(autoScrollRunnable, 3000) // 页面可见时重新开始轮播
        } catch (e: Exception) {
            // 处理轮播图启动异常
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            autoScrollHandler.removeCallbacks(autoScrollRunnable) // 销毁View时停止轮播
            _binding = null
        } catch (e: Exception) {
            // 处理资源清理异常
        }
    }
} 