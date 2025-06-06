package com.greendrading.app.ui.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.R

class QuickSellFragment : Fragment() {

    private lateinit var mainImage: ImageView
    private lateinit var logisticsImage1: ImageView
    private lateinit var logisticsImage2: ImageView
    private lateinit var logisticsImage3: ImageView
    private lateinit var btnBack: ImageButton
    private lateinit var btnMoveImage: TextView
    private lateinit var rbSelfDelivery: RadioButton
    private lateinit var rbPlatformDelivery: RadioButton
    private lateinit var btnSaveDraft: Button
    private lateinit var btnPublish: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quick_sell, container, false)
        
        // 初始化视图
        mainImage = view.findViewById(R.id.main_image)
        logisticsImage1 = view.findViewById(R.id.logistics_image_1)
        logisticsImage2 = view.findViewById(R.id.logistics_image_2)
        logisticsImage3 = view.findViewById(R.id.logistics_image_3)
        btnBack = view.findViewById(R.id.btn_back)
        btnMoveImage = view.findViewById(R.id.btn_move_image)
        rbSelfDelivery = view.findViewById(R.id.rb_self_delivery)
        rbPlatformDelivery = view.findViewById(R.id.rb_platform_delivery)
        btnSaveDraft = view.findViewById(R.id.btn_save_draft)
        btnPublish = view.findViewById(R.id.btn_publish)

        // 设置点击事件
        setupLogisticsImageClickListeners()
        setupListeners()

        return view
    }

    private fun setupLogisticsImageClickListeners() {
        logisticsImage1.setOnClickListener {
            mainImage.setImageDrawable(logisticsImage1.drawable)
        }

        logisticsImage2.setOnClickListener {
            mainImage.setImageDrawable(logisticsImage2.drawable)
        }

        logisticsImage3.setOnClickListener {
            mainImage.setImageDrawable(logisticsImage3.drawable)
        }
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnMoveImage.setOnClickListener {
            Toast.makeText(requireContext(), "移图功能开发中", Toast.LENGTH_SHORT).show()
        }

        btnSaveDraft.setOnClickListener {
            Toast.makeText(requireContext(), "存草稿功能开发中", Toast.LENGTH_SHORT).show()
        }

        btnPublish.setOnClickListener {
            Toast.makeText(requireContext(), "发布功能开发中", Toast.LENGTH_SHORT).show()
        }
    }
} 