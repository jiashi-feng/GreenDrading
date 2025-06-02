package com.example.greendrading

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // 加载首页Fragment
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_shop -> {
                    // 加载购物Fragment
                    loadFragment(ShopFragment())
                    true
                }
                R.id.navigation_publish -> {
                    // 启动发布Activity
                    loadFragment(PublishFragment())
                    true
                }
                R.id.navigation_message -> {
                    // 加载消息Fragment
                    loadFragment(MessageFragment())
                    true
                }
                R.id.navigation_profile -> {
                    // 加载个人中心Fragment
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // 设置默认选中的Fragment
        bottomNav.selectedItemId = R.id.navigation_home
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
} 