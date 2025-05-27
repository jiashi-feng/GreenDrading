package com.greendrading.app.ui.publish

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.greendrading.app.R
import com.greendrading.app.databinding.ActivityPublishBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublishActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPublishBinding
    private lateinit var publishTabsAdapter: PublishTabsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupViewPager()
        setupTabLayout()
        setupFab()
        setupBackNavigation()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupViewPager() {
        publishTabsAdapter = PublishTabsAdapter(this)
        binding.viewPager.adapter = publishTabsAdapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "晒好物"
                1 -> "拍图自己卖"
                2 -> "挂售"
                3 -> "寄售"
                else -> ""
            }
        }.attach()
    }

    private fun setupFab() {
        binding.fabPublish.setOnClickListener {
            // 可以添加一些视觉反馈，比如轻微的动画
        }
    }

    private fun setupBackNavigation() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
} 