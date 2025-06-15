package com.greendrading.app

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * 确认支付Fragment
 * 异常处理说明：
 * 1. 视图相关异常：
 *    - IllegalStateException: 当视图创建失败时
 *    - NullPointerException: 当视图ID未找到时
 * 2. 对话框相关异常：
 *    - IllegalStateException: 当对话框创建失败时
 *    - WindowManager.BadTokenException: 当对话框显示失败时
 * 3. 输入验证相关异常：
 *    - IllegalArgumentException: 当输入数据无效时
 * 4. 生命周期相关异常：
 *    - IllegalStateException: 当在Fragment销毁后访问视图时
 * 建议的异常处理方式：
 * - 使用安全调用操作符（?.）和空值合并操作符（?:）
 * - 在关键操作处添加try-catch块
 * - 实现适当的错误恢复机制
 * - 记录异常日志以便调试
 */
class ConfirmPaymentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            // 注意：此处可能抛出IllegalStateException
            return inflater.inflate(R.layout.fragment_confirm_payment, container, false)
        } catch (e: Exception) {
            // 处理视图创建异常
            return null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            view.findViewById<View>(R.id.backButton)?.setOnClickListener {
                // 跳转到首页
                requireActivity().supportFragmentManager.popBackStack(null, 0)
            }

            val addressInfo = view.findViewById<TextView>(R.id.addressInfo)
            val btnEditAddress = view.findViewById<Button>(R.id.btnEditAddress)
            btnEditAddress?.setOnClickListener {
                try {
                    // 弹出输入框
                    val dialogView = LayoutInflater.from(requireContext()).inflate(android.R.layout.simple_list_item_2, null)
                    val inputName = EditText(requireContext())
                    inputName.hint = "姓名"
                    val inputPhone = EditText(requireContext())
                    inputPhone.hint = "联系方式"
                    val inputAddress = EditText(requireContext())
                    inputAddress.hint = "地址"
                    val layout = android.widget.LinearLayout(requireContext())
                    layout.orientation = android.widget.LinearLayout.VERTICAL
                    layout.addView(inputName)
                    layout.addView(inputPhone)
                    layout.addView(inputAddress)
                    AlertDialog.Builder(requireContext())
                        .setTitle("修改收货地址")
                        .setView(layout)
                        .setPositiveButton("确认") { _, _ ->
                            try {
                                val name = inputName.text.toString()
                                val phone = inputPhone.text.toString()
                                val address = inputAddress.text.toString()
                                // 注意：此处可能抛出IllegalArgumentException
                                if (name.isBlank() || phone.isBlank() || address.isBlank()) {
                                    throw IllegalArgumentException("输入信息不能为空")
                                }
                                addressInfo.text = "收货人：$name  $phone\n地址：$address"
                            } catch (e: Exception) {
                                // 处理输入验证异常
                                Toast.makeText(requireContext(), "请输入完整信息", Toast.LENGTH_SHORT).show()
                            }
                        }
                        .setNegativeButton("取消", null)
                        .show()
                } catch (e: Exception) {
                    // 处理对话框创建和显示异常
                    Toast.makeText(requireContext(), "无法显示地址编辑对话框", Toast.LENGTH_SHORT).show()
                }
            }

            val btnConfirmPay = view.findViewById<Button>(R.id.btnConfirmPay)
            btnConfirmPay?.setOnClickListener {
                try {
                    // 居中显示支付成功
                    val toast = Toast.makeText(requireContext(), "支付成功", Toast.LENGTH_SHORT)
                    toast.setGravity(android.view.Gravity.CENTER, 0, 0)
                    toast.show()
                } catch (e: Exception) {
                    // 处理Toast显示异常
                }
            }
        } catch (e: Exception) {
            // 处理视图初始化和事件绑定异常
        }
    }
}
