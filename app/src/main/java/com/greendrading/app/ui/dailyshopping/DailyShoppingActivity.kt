package com.greendrading.app.ui.dailyshopping

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.greendrading.app.R
import com.greendrading.app.databinding.ActivityDailyShoppingBinding
import com.google.android.material.tabs.TabLayout

class DailyShoppingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDailyShoppingBinding
    private lateinit var couponAdapter: CouponAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()
        loadCoupons()
    }

    private fun setupViews() {
        couponAdapter = CouponAdapter(emptyList()) {
            // Handle coupon item click
            Toast.makeText(this, "点击了优惠券: ${it.category}", Toast.LENGTH_SHORT).show()
        }
        binding.rvCoupons.apply {
            layoutManager = LinearLayoutManager(this@DailyShoppingActivity)
            adapter = couponAdapter
        }

        // Setup category tabs
        binding.tabCategories.addTab(binding.tabCategories.newTab().setText("神券秒杀"))
        binding.tabCategories.addTab(binding.tabCategories.newTab().setText("花卉"))
        binding.tabCategories.addTab(binding.tabCategories.newTab().setText("绿植"))
        binding.tabCategories.addTab(binding.tabCategories.newTab().setText("园林景艺"))
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.btnShare.setOnClickListener {
            Toast.makeText(this, "分享功能开发中", Toast.LENGTH_SHORT).show()
        }
        binding.btnMore.setOnClickListener {
            Toast.makeText(this, "更多功能开发中", Toast.LENGTH_SHORT).show()
        }

        binding.tabCategories.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val category = tab?.text.toString()
                Toast.makeText(this@DailyShoppingActivity, "切换到类别: $category", Toast.LENGTH_SHORT).show()
                // Here you would typically filter coupons based on category
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.fabMyCoupons.setOnClickListener {
            Toast.makeText(this, "我的优惠券功能开发中", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadCoupons() {
        val coupons = listOf(
            Coupon("1", R.drawable.youhui, "绿植299-100券", "¥ 100", "满299可用", "00 : 28 : 12"),
            Coupon("2", R.drawable.youhui, "绿植299-100券", "¥ 100", "满299可用", "00 : 28 : 12"),
            Coupon("3", R.drawable.youhui, "绿植299-100券", "¥ 100", "满299可用", "00 : 28 : 12"),
            Coupon("4", R.drawable.youhui, "绿植299-100券", "¥ 100", "满299可用", "00 : 28 : 12")
        )
        couponAdapter.updateCoupons(coupons)
    }
} 