package com.greendrading.app.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.greendrading.app.R

@Composable
fun OnlineCustomerServiceScreen(navController: NavController) {
    Log.d("OnlineCustomerServiceScreen", "OnlineCustomerServiceScreen is being composed")

    val hotIssues = listOf(
        "违规如何申诉?",
        "软件服务费具体怎么收?",
        "如何申请交易纠纷介入?",
        "如何开通绿韵花坊小铺?",
        "买家不确认收货，怎么办?",
        "如何注销账号?",
        "绿韵花坊如何换绑支付宝账号?",
        "如何举报骗子?",
        "卖家不发货怎么办?",
        "收到商品有问题怎么办?",
        "如何申请退款?"
    )

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
                text = "在线客服",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "我的事项", tint = Color.Black, modifier = Modifier.size(24.dp))
                Text(
                    text = "1",
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .offset(x = (-8).dp, y = (-8).dp) // Adjust position to be on top right of icon
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(Color.Red)
                        .wrapContentSize(Alignment.Center)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("我的事项", fontSize = 14.sp, color = Color.Black)
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            // Hi, 以下事项请你关注
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFFFF7E6)) // Light yellow background
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Hi,", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        Text("以下事项请你关注", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_customer_service_character), // Placeholder for the character image
                        contentDescription = "客服形象",
                        modifier = Modifier.size(80.dp)
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }

            // 请尽快完成实人认证
            item {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "请尽快完成实人认证",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "核实到你未完成实人认证，实人认证是绿韵花坊交易安全的重要一环，为保障你和他人的交易安全，请尽快完成实人认证",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            lineHeight = 20.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /* TODO: Go to real-name verification */ },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFDD835)), // Yellow button
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                        ) {
                            Text("实人认证", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            TextButton(onClick = { /* TODO: Why verification failed */ }) {
                                Text("为何实人认证不通...", color = Color.Gray, fontSize = 14.sp)
                            }
                            TextButton(onClick = { /* TODO: More related questions */ }) {
                                Text("更多相关问题", color = Color.Gray, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Search Bar
            item {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* TODO: Handle search input */ },
                    placeholder = { Text("查找\"退款\"相关问题", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "搜索") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Quick Tools
            item {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        QuickToolItem(Icons.Outlined.Info, "快速举报") { /* TODO: Navigate */ }
                        QuickToolItem(Icons.Outlined.Notifications, "违规查询") { /* TODO: Navigate */ }
                        QuickToolItem(Icons.Outlined.AccountCircle, "账号管理") { /* TODO: Navigate */ }
                        QuickToolItem(Icons.Outlined.ShoppingCart, "查看钱款") { /* TODO: Navigate */ }
                        QuickToolItem(Icons.Outlined.PlayArrow, "全部工具") { /* TODO: Navigate */ }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Hot Issues / Tabs
            item {
                TabRow(
                    selectedTabIndex = 0, // TODO: Implement actual tab selection logic
                    backgroundColor = Color.White,
                    contentColor = Color(0xFF0A7A5A), // Selected tab indicator color
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Tab(selected = true, onClick = { /* TODO: Select tab */ }) { Text("热点问题", modifier = Modifier.padding(vertical = 12.dp)) }
                    Tab(selected = false, onClick = { /* TODO: Select tab */ }) { Text("交易/维权", modifier = Modifier.padding(vertical = 12.dp)) }
                    Tab(selected = false, onClick = { /* TODO: Select tab */ }) { Text("账户管理", modifier = Modifier.padding(vertical = 12.dp)) }
                    Tab(selected = false, onClick = { /* TODO: Select tab */ }) { Text("欺诈/举报", modifier = Modifier.padding(vertical = 12.dp)) }
                    // Add more tabs as needed
                }
                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
            }

            items(hotIssues) {
                QuestionItem(it) { /* TODO: Navigate to question detail */ }
                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Online Customer Service
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { /* TODO: Start chat with customer service */ },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0A7A5A)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Icon(painterResource(id = R.drawable.ic_online_customer_service), contentDescription = "在线客服", tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("在线客服", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "客服热线: 9510222 (9:00-21:00)",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun QuickToolItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp) // Add padding for click area
    ) {
        Icon(icon, contentDescription = label, tint = Color(0xFF0A7A5A), modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp, color = Color.Black)
    }
}

@Composable
fun QuestionItem(question: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(question, fontSize = 15.sp, color = Color.Black)
        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.Gray)
    }
} 