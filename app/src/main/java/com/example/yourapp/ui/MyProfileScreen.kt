@file:OptIn(androidx.compose.material.ExperimentalMaterialApi::class)
package com.example.yourapp.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.greendrading.app.R

// 我的界面主屏幕 Composable，展示用户信息、统计、交易入口和其他功能
@Composable
fun MyProfileScreen(navController: androidx.navigation.NavController) {
    val context = LocalContext.current
    val userInfo = remember {
        UserInfo(
            avatarUrl = "",
            username = "鱼缸边的猫",
            follow = 0,
            fans = 0,
            collect = 7,
            history = 43,
            myFollow = 5,
            coupon = 2
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 0.dp, vertical = 0.dp)
    ) {
        // 顶部个人信息区
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0A7A5A))
                .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                .padding(top = 32.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // 头像（圆形，支持点击更换）
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = R.drawable.avatar),
                    contentDescription = "头像",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { Toast.makeText(context, "更换头像", Toast.LENGTH_SHORT).show() },
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    // 用户名（支持点击修改）
                    Text(
                        text = userInfo.username,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { Toast.makeText(context, "修改用户名", Toast.LENGTH_SHORT).show() }
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // 关注数（可点击跳转）
                        Text(
                            text = "关注 ${userInfo.follow}",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.clickable { Toast.makeText(context, "跳转关注列表", Toast.LENGTH_SHORT).show() }
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        // 粉丝数（可点击跳转）
                        Text(
                            text = "粉丝 ${userInfo.fans}",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.clickable { Toast.makeText(context, "跳转粉丝列表", Toast.LENGTH_SHORT).show() }
                        )
                    }
                }
                // 编辑资料按钮
                TextButton(
                    onClick = { Toast.makeText(context, "编辑资料", Toast.LENGTH_SHORT).show() },
                    modifier = Modifier.height(32.dp)
                ) {
                    Icon(Icons.Outlined.Edit, contentDescription = "编辑", tint = Color.White, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("编辑资料", color = Color.White, fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 四个统计项：我的收藏、历史浏览、我的关注、红包卡券
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileStat("我的收藏", userInfo.collect, modifier = Modifier.weight(1f).clickable { navController.navigate(R.id.my_collection_fragment) })
                ProfileStat("历史浏览", userInfo.history, modifier = Modifier.weight(1f).clickable { navController.navigate(R.id.my_history_fragment) })
                ProfileStat("我的关注", userInfo.myFollow, modifier = Modifier.weight(1f).clickable { navController.navigate(R.id.my_follow_fragment) })
                ProfileStat("红包卡券", userInfo.coupon, modifier = Modifier.weight(1f).clickable { navController.navigate(R.id.my_coupons_fragment) })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 我的交易区块，包含我发布的、我卖出的、我买到的、待评价
        Text(
            text = "我的交易",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color(0xFF333333),
            modifier = Modifier.padding(start = 20.dp, top = 8.dp, bottom = 8.dp)
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TradeItem(Icons.Outlined.Home, "我发布的", modifier = Modifier.clickable { navController.navigate(R.id.my_posts_fragment) })
                TradeItem(Icons.Outlined.Star, "我卖出的", modifier = Modifier.clickable { navController.navigate(R.id.my_sold_items_fragment) })
                TradeItem(Icons.Outlined.ShoppingCart, "我买到的", modifier = Modifier.clickable { navController.navigate(R.id.my_purchased_items_fragment) })
                TradeItem(Icons.Outlined.Star, "待评价", modifier = Modifier.clickable { navController.navigate(R.id.pending_review_fragment) })
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 其他功能区块，包含设置、服务条款、在线客服、关于我们
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Column {
                ProfileListItem(Icons.Outlined.Settings, "设置") { navController.navigate(R.id.settings_fragment) }
                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
                ProfileListItem(Icons.Outlined.Info, "服务条款") { navController.navigate(R.id.service_terms_fragment) }
                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
                ProfileListItem(Icons.Outlined.Info, "在线客服") { navController.navigate(R.id.online_customer_service_fragment) }
                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
                ProfileListItem(Icons.Outlined.Info, "关于我们") { navController.navigate(R.id.about_us_fragment) }
            }
        }
    }
}

// 个人统计项组件
@Composable
fun ProfileStat(label: String, count: Int, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(text = "$count", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF0A7A5A))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, fontSize = 14.sp, color = Color(0xFF666666))
    }
}

// 交易入口项组件
@Composable
fun TradeItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF0A7A5A),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color(0xFF666666)
        )
    }
}

// 个人功能列表项组件
@Composable
fun ProfileListItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        icon = { Icon(icon, contentDescription = label, tint = Color(0xFF8A8A8A), modifier = Modifier.size(22.dp)) },
        text = { Text(label, fontSize = 15.sp, color = Color(0xFF444444)) }
    )
}

// 用户信息数据类
data class UserInfo(
    val avatarUrl: String,
    val username: String,
    val follow: Int,
    val fans: Int,
    val collect: Int,
    val history: Int,
    val myFollow: Int,
    val coupon: Int
)
