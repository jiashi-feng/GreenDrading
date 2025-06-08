package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.greendrading.app.databinding.FragmentProductDetailBinding

class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ProductDetailFragmentArgs by navArgs()

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
    }

    private fun setupUI() {
        // 这里应该根据productId从数据库或API获取商品详情
        // 现在使用模拟数据
        when (args.productId) {
            "product_1" -> {
                binding.productImage.setImageResource(R.drawable.sell_plant_10)
                binding.productTitle.text = "芦荟"
                binding.productPrice.text = "¥20"
                binding.productDescription.text = """
                    商品定价：20元
                    市场价：19元
                    商品定位：易养护植物
                    
                    商品描述：
                    芦荟是一种多肉植物，具有很好的观赏价值和药用价值。
                    易于养护，适合室内种植。
                    本商品为健康生长的芦荟，高度约15-20厘米。
                """.trimIndent()
            }
            // 可以添加更多商品
        }

        binding.buyButton.setOnClickListener {
            // TODO: 实现购买功能
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 