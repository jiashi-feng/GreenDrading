package com.greendrading.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.greendrading.app.R // Make sure this import is correct for your R file
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.border
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.material.TextButton
import androidx.compose.material.Card
import androidx.compose.ui.text.style.TextOverflow

// Data classes for Coupons
data class Coupon(
    val id: Int,
    val type: String, // e.g., "现金券", "满减券"
    val value: String, // e.g., "5", "10"
    val description: String, // e.g., "无金额门槛", "仅自营无门槛10元券"
    val dateRange: String, // e.g., "2025.04.11到2025.05.11"
    val extraInfo: String?, // e.g., "仅限购买工厂店商品", "仅剩1天"
    val isUsed: Boolean = false,
    val isExpired: Boolean = false,
    val minSpend: String? = null // e.g., "满100使用"
)

// Data class for Products (for "猜你喜欢")
data class Product(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val price: String,
    val originalPrice: String? = null,
    val tags: List<String>, // e.g., ["自营", "特价"]
    val rating: String // e.g., "97.8%好评"
)

// 卡券界面主屏幕 Composable，展示用户卡券和推荐商品
@Composable
fun MyCouponsScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("未使用", "已使用", "已过期")

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
                text = "红包卡券",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = "优惠说明",
                fontSize = 14.sp,
                color = Color(0xFF666666),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { /* TODO: Show discount description */ }
            )
        }

        // Tabs
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.White,
            contentColor = Color(0xFF4CAF50), // Indicator color
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) },
                    selectedContentColor = Color(0xFF4CAF50),
                    unselectedContentColor = Color.Gray
                )
            }
        }

        // Sample Data for Coupons
        val allCoupons = remember { listOf(
            Coupon(1, "现金券", "5", "无金额门槛", "2025.04.11到2025.05.11", "仅限购买工厂店商品", isUsed = false, isExpired = false),
            Coupon(2, "满减券", "10", "仅自营无门槛10元券", "2025.04.11到期", "仅剩1天", isUsed = false, isExpired = false, minSpend = "满100使用"),
            Coupon(3, "现金券", "20", "满200使用", "2024.01.01到2024.01.31", null, isUsed = true, isExpired = false, minSpend = "满200使用"),
            Coupon(4, "折扣券", "8折", "全场通用", "2023.12.01到2023.12.31", null, isUsed = false, isExpired = true)
        )}

        val unusedCoupons = allCoupons.filter { !it.isUsed && !it.isExpired }
        val usedCoupons = allCoupons.filter { it.isUsed }
        val expiredCoupons = allCoupons.filter { it.isExpired }

        val currentCoupons = when (selectedTabIndex) {
            0 -> unusedCoupons
            1 -> usedCoupons
            else -> expiredCoupons
        }

        // Main Scrollable Content
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            // Redemption Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* TODO: Handle redemption code input */ },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("请输入兑换码") },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = Color.White,
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray
                    )
                )
                Button(
                    onClick = { /* TODO: Redeem code */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.width(80.dp)
                ) {
                    Text("兑换", color = Color.White)
                }
            }

            // Coupon List
            if (currentCoupons.isEmpty()) {
                Text(
                    text = "暂无优惠券",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp) // Limit height to avoid nested scrolling issues
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(currentCoupons) { coupon ->
                        CouponItem(coupon = coupon)
                    }
                }
            }

            // "用考拉豆兑换几张吧" section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { /* TODO: Navigate to Koala Bean redemption */ },
                shape = RoundedCornerShape(8.dp),
                elevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "用考拉豆兑换几张吧",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Icon(Icons.Filled.ArrowForward, contentDescription = "前往兑换", tint = Color.Gray)
                }
            }

            // "猜你喜欢" section
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "猜你喜欢",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            val recommendedProducts = remember { listOf(
                Product(1, R.drawable.ic_plant_1, "【秒杀特价】栀子花6.9一盆！！花期带花苞", "6.90", null, listOf("自营", "特价"), "97.8%好评"),
                Product(2, R.drawable.ic_plant_2, "三角梅绿植，绿叶樱花三角梅，抹茶色渐变樱花粉", "29.90", null, listOf(), "97.8%好评"),
                Product(3, R.drawable.ic_plant_3, "100支多色带花苞发货重瓣大阳花苗带根", "169.00", null, listOf("自营", "特价"), "97.8%好评"),
                Product(4, R.drawable.ic_plant_4, "金枝玉叶小老桩一颗，公司阳台露养多年，树干枝条木化", "59.90", null, listOf("自营", "特价"), "97.8%好评")
            )}

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 600.dp) // Adjust max height as needed
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(recommendedProducts) { product ->
                    ProductItem(product = product)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("已经到底咯", modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), textAlign = TextAlign.Center, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyCouponsScreen() {
    MyCouponsScreen(navController = rememberNavController())
}

// 卡券项组件，展示单个卡券的详细信息
@Composable
fun CouponItem(coupon: Coupon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (coupon.isUsed || coupon.isExpired) Color(0xFFF0F0F0) else Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Section (Value)
            Column(
                modifier = Modifier.width(90.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "¥${coupon.value}",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (coupon.isUsed || coupon.isExpired) Color.Gray else Color(0xFF4CAF50)
                )
                if (coupon.minSpend != null) {
                    Text(
                        text = coupon.minSpend,
                        fontSize = 12.sp,
                        color = if (coupon.isUsed || coupon.isExpired) Color.Gray else Color(0xFF4CAF50)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Middle Section (Description)
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!coupon.isUsed && !coupon.isExpired) {
                        // Coupon Type Tag
                        Text(
                            text = coupon.type,
                            color = Color.White,
                            fontSize = 10.sp,
                            modifier = Modifier
                                .background(Color(0xFF4CAF50), RoundedCornerShape(4.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text(
                        text = coupon.description,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = if (coupon.isUsed || coupon.isExpired) Color.Gray else Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = coupon.dateRange,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                if (coupon.extraInfo != null) {
                    Text(
                        text = coupon.extraInfo,
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                }
            }

            // Right Section (Button)
            if (!coupon.isUsed && !coupon.isExpired) {
                Button(
                    onClick = { /* TODO: Use coupon */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF44336)),
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                ) {
                    Text("立即使用", color = Color.White, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Visible, modifier = Modifier.wrapContentSize())
                }
            } else if (coupon.isUsed) {
                Text("已使用", color = Color.Gray, fontSize = 14.sp)
            } else if (coupon.isExpired) {
                Text("已过期", color = Color.Gray, fontSize = 14.sp)
            }
        }
        // TODO: Add expand/collapse arrow
    }
}

// 推荐商品项组件
@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to product detail */ },
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = product.title,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "¥${product.price}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE53935) // Red color for price
                    )
                    if (product.originalPrice != null) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "¥${product.originalPrice}",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        product.tags.forEach { tag ->
                            Text(
                                text = tag,
                                fontSize = 10.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .background(Color(0xFFE53935), RoundedCornerShape(4.dp))
                                    .padding(horizontal = 4.dp, vertical = 2.dp)
                            )
                        }
                    }
                    Text(
                        text = product.rating,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
} 