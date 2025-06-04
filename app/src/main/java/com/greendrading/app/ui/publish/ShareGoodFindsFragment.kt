package com.greendrading.app.ui.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.databinding.FragmentShareGoodFindsBinding

class ShareGoodFindsFragment : Fragment() {
    private var _binding: FragmentShareGoodFindsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareGoodFindsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 设置返回按钮点击事件
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // TODO: 实现其他功能按钮的点击事件
        // 1. 照片/视频上传
        // 2. 添加标签
        // 3. 公开/私密设置
        // 4. 存草稿
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 