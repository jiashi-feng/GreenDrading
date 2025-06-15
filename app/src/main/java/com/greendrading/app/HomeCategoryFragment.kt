package com.greendrading.app

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.databinding.FragmentHomeCategoryBinding

/**
 * 首页分类Fragment
 * 异常处理说明：
 * 1. 视图绑定相关异常：
 *    - IllegalStateException: 当视图绑定为空时访问binding属性
 *    - NullPointerException: 当视图ID未找到时
 * 2. 资源相关异常：
 *    - Resources.NotFoundException: 当颜色资源未找到时
 * 3. 类型转换相关异常：
 *    - ClassCastException: 当视图类型转换失败时
 * 4. 生命周期相关异常：
 *    - IllegalStateException: 当在Fragment销毁后访问视图时
 * 建议的异常处理方式：
 * - 使用安全调用操作符（?.）和空值合并操作符（?:）
 * - 在关键操作处添加try-catch块
 * - 实现适当的错误恢复机制
 * - 记录异常日志以便调试
 */
class HomeCategoryFragment : Fragment() {
    private var _binding: FragmentHomeCategoryBinding? = null
    private val binding get() = _binding!!

    private var currentSelectedCategoryView: TextView? = null

    // List of all content LinearLayouts on the right side
    private lateinit var contentPanels: List<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        try {
            // 注意：此处可能抛出IllegalStateException
            _binding = FragmentHomeCategoryBinding.inflate(inflater, container, false)
            return binding.root
        } catch (e: Exception) {
            // 处理视图创建异常
            throw IllegalStateException("Failed to create category view", e)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            // Initialize content panels
            contentPanels = listOf(
                binding.contentObservationalPlants,
                binding.contentEdiblePlants,
                binding.contentMedicinalPlants,
                binding.contentEconomicCrops,
                binding.contentEcologicalRestoration,
                binding.contentReligiousCultural,
                binding.contentTools
            )

            // Set default selected "观赏植物"
            currentSelectedCategoryView = binding.categoryObservational
            currentSelectedCategoryView?.setBackgroundColor(Color.WHITE)
            currentSelectedCategoryView?.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_green))

            // Set all content panels to GONE except the first one
            contentPanels.forEach { it.visibility = View.GONE }
            binding.contentObservationalPlants.visibility = View.VISIBLE // Show default content

            // Set back button click event
            binding.backButton.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack(null, 0)
            }

            // Setup primary category click events
            setupCategoryClickListeners()
        } catch (e: Exception) {
            // 处理视图初始化和事件绑定异常
        }
    }

    private fun setupCategoryClickListeners() {
        try {
            val categories = listOf(
                binding.categoryObservational to binding.contentObservationalPlants,
                binding.categoryEdible to binding.contentEdiblePlants,
                binding.categoryMedicinal to binding.contentMedicinalPlants,
                binding.categoryEconomic to binding.contentEconomicCrops,
                binding.categoryEcological to binding.contentEcologicalRestoration,
                binding.categoryCultural to binding.contentReligiousCultural,
                binding.categoryTools to binding.contentTools
            )

            for ((categoryView, contentPanel) in categories) {
                categoryView.setOnClickListener {
                    try {
                        // Reset previous selection
                        currentSelectedCategoryView?.setBackgroundColor(Color.parseColor("#F8F8F8")) // Default gray background
                        currentSelectedCategoryView?.setTextColor(Color.parseColor("#333333")) // Default black text

                        // Set current selected state
                        it.setBackgroundColor(Color.WHITE) // White background for selected
                        (it as TextView).setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_green)) // Green text for selected
                        currentSelectedCategoryView = it

                        // Hide all content panels
                        contentPanels.forEach { panel -> panel.visibility = View.GONE }

                        // Show the corresponding content panel
                        contentPanel.visibility = View.VISIBLE
                    } catch (e: Exception) {
                        // 处理分类切换异常
                    }
                }
            }
        } catch (e: Exception) {
            // 处理分类点击事件设置异常
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