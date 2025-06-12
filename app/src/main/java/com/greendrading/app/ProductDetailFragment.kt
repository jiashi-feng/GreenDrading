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
               
            }
            // 可以添加更多商品
        }

        binding.addToCartButton.setOnClickListener {
            Toast.makeText(requireContext(), "添加成功", Toast.LENGTH_SHORT).show()
        }

        binding.backButton.setOnClickListener {
            // 跳转到首页
            findNavController().navigate(R.id.navigation_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 