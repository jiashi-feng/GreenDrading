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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

class QuickSellFragment : Fragment() {

    private lateinit var mainImage: ImageView
    private lateinit var btnBack: ImageButton
    private lateinit var btnMoveImage: TextView
    private lateinit var logisticsImagesRecyclerView: RecyclerView
    private lateinit var rbSelfDelivery: RadioButton
    private lateinit var rbPlatformDelivery: RadioButton
    private lateinit var btnSaveDraft: Button
    private lateinit var btnPublish: Button

    private val logisticsImages = listOf(
        R.drawable.ic_share_1,
        R.drawable.ic_share_2,
        R.drawable.ic_share_3,
        R.drawable.ic_share_4
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quick_sell, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupListeners()
        setupLogisticsImages()
        
        // 设置主图
        mainImage.setImageResource(R.drawable.ic_share_1)
    }

    private fun initViews(view: View) {
        mainImage = view.findViewById(R.id.main_image)
        btnBack = view.findViewById(R.id.btn_back)
        btnMoveImage = view.findViewById(R.id.btn_move_image)
        logisticsImagesRecyclerView = view.findViewById(R.id.logistics_images)
        rbSelfDelivery = view.findViewById(R.id.rb_self_delivery)
        rbPlatformDelivery = view.findViewById(R.id.rb_platform_delivery)
        btnSaveDraft = view.findViewById(R.id.btn_save_draft)
        btnPublish = view.findViewById(R.id.btn_publish)
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

    private fun setupLogisticsImages() {
        logisticsImagesRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        
        val adapter = LogisticsImageAdapter(logisticsImages) { position ->
            Toast.makeText(requireContext(), "点击了第${position + 1}张物流图", Toast.LENGTH_SHORT).show()
        }
        logisticsImagesRecyclerView.adapter = adapter
    }
} 