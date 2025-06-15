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

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()

    private val handler = Handler(Looper.getMainLooper())
    private val carouselRunnable = object : Runnable {
        override fun run() {
            if (binding.productImageCarousel.adapter != null && binding.productImageCarousel.adapter!!.itemCount > 0) {
                val currentItem = binding.productImageCarousel.currentItem
                val nextItem = (currentItem + 1) % binding.productImageCarousel.adapter!!.itemCount
                binding.productImageCarousel.setCurrentItem(nextItem, true)
            }
            handler.postDelayed(this, 3000) // Scroll every 3 seconds
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupImageCarousel()
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(carouselRunnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(carouselRunnable)
    }

    private fun setupUI() {
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
    }

    private fun setupImageCarousel() {
        val images = listOf(
            R.drawable.duorou1,
            R.drawable.duorou2,
            R.drawable.duorou4
        )
        val adapter = ImageCarouselAdapter(images)
        binding.productImageCarousel.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 