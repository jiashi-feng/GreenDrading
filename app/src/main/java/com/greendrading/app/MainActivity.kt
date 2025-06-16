package com.greendrading.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * 主活动类
 * 异常处理说明：
 * 1. onCreate中可能出现的异常：
 *    - IllegalStateException: 当NavHostFragment未找到时抛出
 *    - ClassCastException: 当Fragment类型转换失败时抛出
 *    - NullPointerException: 当视图ID未找到时抛出
 * 2. 建议的异常处理方式：
 *    - 使用try-catch捕获可能的异常
 *    - 在异常发生时提供用户友好的错误提示
 *    - 记录异常日志以便调试
 */
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 设置Navigation
        // 注意：此处可能抛出IllegalStateException和ClassCastException
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 设置底部导航
        // 注意：此处可能抛出NullPointerException
        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setupWithNavController(navController)
    }
} 