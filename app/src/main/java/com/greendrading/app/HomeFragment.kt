package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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

        // 设置商品项点击事件
        binding.item1.setOnClickListener {
            // 这里使用示例商品ID，实际应用中应该使用真实的商品ID
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToProductDetailFragment("product_1")
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 