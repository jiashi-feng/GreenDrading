package com.greendrading.app.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Log.d("SettingsScreen", "SettingsScreen is being composed")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Top AppBar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.White)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "返回", tint = Color.Black)
            }
            Text(
                text = "设置",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            // Section: 个人
            item {
                SettingsSectionHeader(text = "个人")
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column {
                        SettingsItem(label = "个人资料") { /* TODO: Navigate to Personal Profile */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "地址管理") { /* TODO: Navigate to Address Management */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "尺码小助手") { /* TODO: Navigate to Size Assistant */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "账号与安全") { /* TODO: Navigate to Account & Security */ }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section: 交易
            item {
                SettingsSectionHeader(text = "交易")
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    SettingsItem(label = "支付方式", subText = "免密支付等") { /* TODO: Navigate to Payment Methods */ }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section: 通用
            item {
                SettingsSectionHeader(text = "通用")
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column {
                        SettingsItem(label = "图片视频") { /* TODO: Navigate to Image/Video Settings */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "消息通知", subText = "自动回复移到这里啦") { /* TODO: Navigate to Notification Settings */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "长辈模式", subText = "未开启") { /* TODO: Navigate to Elder Mode */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "清除缓存") { /* TODO: Clear Cache */ }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section: 隐私
            item {
                SettingsSectionHeader(text = "隐私")
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column {
                        SettingsItem(label = "隐私设置") { /* TODO: Navigate to Privacy Settings */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "黑名单") { /* TODO: Navigate to Blacklist */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "个人信息采集清单") { /* TODO: Navigate to Personal Data Collection List */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "个人信息共享清单") { /* TODO: Navigate to Personal Data Sharing List */ }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section: 关于
            item {
                SettingsSectionHeader(text = "关于")
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column {
                        SettingsItem(label = "关于绿韵花坊") { /* TODO: Navigate to About Xianyu */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "给绿韵花坊好评吧") { /* TODO: Rate App */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "认证招商") { /* TODO: Certified Merchant */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "把绿韵花坊推荐给朋友") { /* TODO: Recommend to Friends */ }
                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                        SettingsItem(label = "绿韵花坊公约") { /* TODO: Xianyu Convention */ }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Logout Button
            item {
                Button(
                    onClick = { /* TODO: Logout */ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    elevation = ButtonDefaults.elevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Text("退出登录", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun SettingsSectionHeader(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color(0xFF666666),
        modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 8.dp)
    )
}

@Composable
fun SettingsItem(label: String, subText: String? = null, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 16.sp, color = Color.Black)
        Row(verticalAlignment = Alignment.CenterVertically) {
            subText?.let {
                Text(text = it, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.Gray)
        }
    }
} 