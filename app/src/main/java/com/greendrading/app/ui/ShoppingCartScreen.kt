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
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.greendrading.app.R

// Data classes
data class CartItem(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val price: Double,
    val originalPrice: Double? = null,
    val quantity: Int,
    var isSelected: Boolean = true,
    val tags: List<String> = emptyList(), // e.g., "包邮", "官方立减"
    val couponInfo: String? = null, // e.g., "消费券 ¥858", "已包含平台和店铺优惠合计342"
    val extraInfo: String? = null // e.g., "即将售罄", "低于同款均价9%", "聚划算 6月20日23:59结束"
)

data class Shop(
    val id: Int,
    val name: String,
    var isSelected: Boolean = true,
    val items: List<CartItem>
)

@Composable
fun ShoppingCartScreen(navController: NavController) {
    val shops = remember { mutableStateListOf(
        Shop(1, "天猫 绿植旗舰店", items = listOf(
            CartItem(1, R.drawable.round_plant_1, "琴叶榕大型盆栽绿植 客厅净化空气", 168.0, 198.0, 1,
                tags = listOf("官方立减180元", "3期免息"),
                couponInfo = "消费券 ¥858",
                extraInfo = "即将售罄"),
            CartItem(2, R.drawable.round_plant_2, "龟背竹盆栽大叶植物 室内净化", 79.9, 99.0, 1,
                tags = listOf("买2送1", "满减券"),
                couponInfo = "已包含平台和店铺优惠合计342",
                extraInfo = "低于同款均价9%")
        )),
        Shop(2, "天猫 花卉园艺店", items = listOf(
            CartItem(3, R.drawable.round_plant_3, "多肉植物组合盆栽 办公室桌面", 29.9, 49.0, 1,
                tags = listOf("多买优惠", "新用户专享"),
                couponInfo = "领券 ¥16.1",
                extraInfo = "买就送花盆")
        )),
        Shop(3, "天猫 鲜花速递", items = listOf(
            CartItem(4, R.drawable.round_plant_4, "玫瑰花束鲜花速递 生日祝福", 128.0, 158.0, 1,
                tags = listOf("同城配送", "限时特惠"),
                couponInfo = "满200减50",
                extraInfo = "618特惠"),
            CartItem(5, R.drawable.round_plant_5, "永生花礼盒送女友 生日礼物", 239.0, 299.0, 1,
                tags = listOf("精美包装", "进口花材"),
                couponInfo = "满300减80",
                extraInfo = "热销款")
        ))
    ) }

    var selectedAll by remember { mutableStateOf(false) }
    val totalAmount = remember(shops) {
        shops.sumOf {
            it.items.filter { item -> item.isSelected }.sumOf { item -> item.price * item.quantity }
        }
    }

    LaunchedEffect(selectedAll) {
        shops.forEach { shop ->
            shop.isSelected = selectedAll
            shop.items.forEach { item -> item.isSelected = selectedAll }
        }
    }

    Scaffold(
        topBar = { TopAppBarContent() },
        bottomBar = { BottomSummaryBar(selectedAll, totalAmount) { selectedAll = it } }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5))
        ) {
            item { TopOptionsBar() }

            items(shops) { shop ->
                ShopSection(shop) { shopId, itemId, isSelected ->
                    val currentShop = shops.find { it.id == shopId }
                    currentShop?.let {
                        val currentItem = it.items.find { item -> item.id == itemId }
                        currentItem?.isSelected = isSelected
                        it.isSelected = it.items.all { item -> item.isSelected }
                        selectedAll = shops.all { s -> s.isSelected }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun TopAppBarContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "购物车 (300)",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterStart)
        )
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = "搜索", tint = Color.Gray, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "管理",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.clickable { /* TODO: Implement manage mode */ }
            )
        }
    }
}

@Composable
fun TopOptionsBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TopOption(
            icon = Icons.Outlined.List,
            text = "消费券",
            onClick = { /* TODO: Filter by consumer coupons */ },
            modifier = Modifier.weight(1f)
        )
        TopOption(
            icon = Icons.Outlined.KeyboardArrowDown,
            text = "降价",
            onClick = { /* TODO: Sort by price drop */ },
            modifier = Modifier.weight(1f)
        )
        TopOption(
            icon = Icons.Outlined.ShoppingCart,
            text = "分组",
            onClick = { /* TODO: Group items */ },
            modifier = Modifier.weight(1f)
        )
        TopOption(
            icon = Icons.Outlined.List,
            text = "常购",
            onClick = { /* TODO: Show frequently purchased */ },
            modifier = Modifier.weight(1f)
        )
    }
    Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
}

@Composable
fun TopOption(icon: ImageVector, text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(icon, contentDescription = text, tint = Color.Gray, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text, fontSize = 14.sp, color = Color.Gray)
    }
}

@Composable
fun ShopSection(shop: Shop, onItemSelected: (Int, Int, Boolean) -> Unit) {
    var isShopSelected by remember(shop) { mutableStateOf(shop.isSelected) }

    LaunchedEffect(shop.isSelected) {
        isShopSelected = shop.isSelected
    }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: Navigate to shop */ }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = isShopSelected,
                        onCheckedChange = { checked ->
                            isShopSelected = checked
                            shop.items.forEach { item -> item.isSelected = checked }
                            onItemSelected(shop.id, -1, checked)
                        },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFF0A7A5A))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = shop.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.Gray, modifier = Modifier.size(18.dp))
                }
                Text(
                    text = "领券",
                    fontSize = 14.sp,
                    color = Color(0xFF0A7A5A),
                    modifier = Modifier.clickable { /* TODO: Get coupons for shop */ }
                )
            }

            shop.items.forEachIndexed { index, item ->
                CartItemCard(item = item) { itemId, isSelected ->
                    onItemSelected(shop.id, itemId, isSelected)
                    isShopSelected = shop.items.all { it.isSelected }
                }
                if (index < shop.items.lastIndex) {
                    Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem, onItemSelection: (Int, Boolean) -> Unit) {
    var isItemSelected by remember(item) { mutableStateOf(item.isSelected) }

    LaunchedEffect(item.isSelected) {
        isItemSelected = item.isSelected
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isItemSelected,
            onCheckedChange = { checked ->
                isItemSelected = checked
                onItemSelection(item.id, checked)
            },
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF0A7A5A))
        )
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.title,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.title,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                items(item.tags) { tag ->
                    Text(
                        text = tag,
                        fontSize = 10.sp,
                        color = Color(0xFF0A7A5A),
                        modifier = Modifier
                            .background(Color(0xFFE0F2F1), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            item.couponInfo?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
            item.extraInfo?.let {
                Text(
                    text = it,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "¥${item.price}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            item.originalPrice?.let {
                Text(
                    text = "¥${it}",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "-",
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.LightGray)
                        .clickable { /* TODO: Decrease quantity */ }
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
                Text(
                    text = "${item.quantity}",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "+",
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.LightGray)
                        .clickable { /* TODO: Increase quantity */ }
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
fun BottomSummaryBar(selectedAll: Boolean, totalAmount: Double, onSelectAll: (Boolean) -> Unit) {
    Column {
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(modifier = Modifier.align(Alignment.CenterStart)) {
                Text(
                    text = "当前未使用9折消费券，剩余3张共900元可用",
                    fontSize = 12.sp,
                    color = Color(0xFFFF9800),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedAll,
                    onCheckedChange = onSelectAll,
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF0A7A5A))
                )
                Text("全选", fontSize = 16.sp, color = Color.Black)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("合计: ", fontSize = 16.sp, color = Color.Black)
                Text(
                    text = "¥${String.format("%.2f", totalAmount)}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { /* TODO: Go to checkout */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF5722)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .height(48.dp)
                        .width(100.dp)
                ) {
                    Text("结算", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
} 