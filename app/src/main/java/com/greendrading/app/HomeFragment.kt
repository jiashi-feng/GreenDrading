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
            val currentItem = binding.carouselViewPager.currentItem
            val nextItem = if (currentItem == carouselImages.size - 1) 0 else currentItem + 1
            binding.carouselViewPager.currentItem = nextItem
            autoScrollHandler.postDelayed(this, 3000) // 每3秒滑动一次
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        setupCarousel()
    }

    private fun setupClickListeners() {
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
            // 这里使用示例商品ID，实际应用中应该使用真实的商品ID
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProductDetailFragment("product_1")
            )
        }
    }

    private fun setupCarousel() {
        val adapter = CarouselAdapter(carouselImages)
        binding.carouselViewPager.adapter = adapter

        // 启动自动轮播
        autoScrollHandler.postDelayed(autoScrollRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        autoScrollHandler.removeCallbacks(autoScrollRunnable) // 页面不可见时停止轮播
    }

    override fun onResume() {
        super.onResume()
        autoScrollHandler.postDelayed(autoScrollRunnable, 3000) // 页面可见时重新开始轮播
    }

    override fun onDestroyView() {
        super.onDestroyView()
        autoScrollHandler.removeCallbacks(autoScrollRunnable) // 销毁View时停止轮播
        _binding = null
    }
} 