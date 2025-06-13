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

class ConfirmPaymentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirm_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.backButton)?.setOnClickListener {
            // 跳转到首页
            requireActivity().supportFragmentManager.popBackStack(null, 0)
        }

        val addressInfo = view.findViewById<TextView>(R.id.addressInfo)
        val btnEditAddress = view.findViewById<Button>(R.id.btnEditAddress)
        btnEditAddress?.setOnClickListener {
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
                    val name = inputName.text.toString()
                    val phone = inputPhone.text.toString()
                    val address = inputAddress.text.toString()
                    addressInfo.text = "收货人：$name  $phone\n地址：$address"
                }
                .setNegativeButton("取消", null)
                .show()
        }

        val btnConfirmPay = view.findViewById<Button>(R.id.btnConfirmPay)
        btnConfirmPay?.setOnClickListener {
            // 居中显示支付成功
            val toast = Toast.makeText(requireContext(), "支付成功", Toast.LENGTH_SHORT)
            toast.setGravity(android.view.Gravity.CENTER, 0, 0)
            toast.show()
        }
    }
}
