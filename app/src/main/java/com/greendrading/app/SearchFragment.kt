package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * 搜索Fragment
 * 异常处理说明：
 * 1. 视图绑定相关异常：
 *    - IllegalStateException: 当视图绑定失败时
 *    - NullPointerException: 当视图ID未找到时
 * 2. 导航相关异常：
 *    - IllegalStateException: 当导航操作无效时
 *    - IllegalArgumentException: 当导航参数无效时
 * 3. 生命周期相关异常：
 *    - IllegalStateException: 当在Fragment销毁后访问视图时
 * 建议的异常处理方式：
 * - 使用安全调用操作符（?.）和空值合并操作符（?:）
 * - 在关键操作处添加try-catch块
 * - 实现适当的错误恢复机制
 * - 记录异常日志以便调试
 */
class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 注意：此处可能抛出IllegalStateException
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            // 初始化搜索页面的逻辑
            val binding = com.greendrading.app.databinding.FragmentSearchBinding.bind(view)
            binding.btnSearch.setOnClickListener {
                findNavController().navigate(R.id.shoppingListFragment)
            }
            binding.btnCancel.setOnClickListener {
                findNavController().navigate(R.id.navigation_home)
            }

            // Set up click listener for "多肉" TextView
            view.findViewById<TextView>(R.id.tvDuoRou).setOnClickListener {
                findNavController().navigate(R.id.shoppingListFragment)
            }

            // Set up cancel button click listener
            view.findViewById<TextView>(R.id.btnCancel).setOnClickListener {
                findNavController().navigateUp()
            }
        } catch (e: Exception) {
            // 处理视图初始化和事件绑定异常
        }
    }
} 