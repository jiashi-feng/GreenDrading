package com.greendrading.app.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.greendrading.app.R // Make sure this import is correct for your R file

// Data class for a Purchased Item
data class PurchasedItem(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val price: String,
    val quantity: Int,
    val status: String, // e.g., "交易成功", "待付款", "待发货", "已发货"
    val date: String // e.g., "2025-03-24"
)

@Composable
fun PurchasedItemCard(item: PurchasedItem) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Date and Status Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.date,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.status,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = when (item.status) {
                            "交易成功", "已发货" -> Color(0xFF0A7A5A)
                            "待付款", "待发货" -> Color(0xFFFF9800)
                            else -> Color.Black
                        }
                    )
                    if (item.status == "交易成功") {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.Delete, contentDescription = "删除", tint = Color.Gray, modifier = Modifier.size(18.dp).clickable { /* TODO: Delete action */ })
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Item Details
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: Navigate to item detail */ },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "查看产品使用说明",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { /* TODO: View instructions */ }
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "¥${item.price}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "x${item.quantity}",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Total Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "共${item.quantity}件",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "应付总额: ",
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = "¥${String.format("%.2f", item.price.toDouble() * item.quantity)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFFE44A3A) // Red color for total
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons based on Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                when (item.status) {
                    "交易成功" -> {
                        OutlinedButton(
                            onClick = { /* TODO: Re-purchase */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("再次购买")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = { /* TODO: View Logistics */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("查看物流")
                        }
                    }
                    "待付款" -> {
                        OutlinedButton(
                            onClick = { /* TODO: Cancel Order */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("取消订单")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { /* TODO: Pay Now */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE44A3A))
                        ) {
                            Text("立即付款", color = Color.White)
                        }
                    }
                    "待发货" -> {
                        OutlinedButton(
                            onClick = { /* TODO: Remind Delivery */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("提醒发货")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = { /* TODO: Cancel Order */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("取消订单")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(
                            onClick = { /* TODO: View Logistics */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("查看物流")
                        }
                    }
                    "已发货" -> {
                        OutlinedButton(
                            onClick = { /* TODO: View Logistics */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                        ) {
                            Text("查看物流")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { /* TODO: Confirm Receipt */ },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0A7A5A))
                        ) {
                            Text("确认收货", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyPurchasedItemsScreen(navController: NavController) {
    Log.d("MyPurchasedItemsScreen", "MyPurchasedItemsScreen is being composed")
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
                text = "我买到的", // Changed title here
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                IconButton(onClick = { /* TODO: Search */ }) {
                    Icon(Icons.Filled.Search, contentDescription = "搜索", tint = Color.Black)
                }
                IconButton(onClick = { /* TODO: More options */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "更多", tint = Color.Black)
                }
            }
        }

        // Purchased Item List
        val purchasedItems = remember { listOf(
            PurchasedItem(1, R.drawable.ic_plant_1, "50颗38个品种风雨兰常年开花300天！", "16.90", 2, "交易成功", "2025-03-24"),
            PurchasedItem(2, R.drawable.ic_plant_2, "【特价2天】发财树盆栽发财树苗室内", "10.80", 1, "待付款", "2025-03-24"),
            PurchasedItem(3, R.drawable.ic_plant_3, "东北玲兰100颗【特价】铃兰花苗带芽网", "9.90", 1, "待发货", "2025-03-24"),
            PurchasedItem(4, R.drawable.ic_plant_4, "室外耐热爆花绿植（粉苞冬红/粉帽子）", "169.00", 1, "已发货", "2025-03-24")
        )}

        // Group items by date (optional, but good for consistency if needed later)
        val groupedItems = purchasedItems.groupBy { it.date }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            groupedItems.forEach { (date, itemsForDate) ->
                // You can add a date header here if you want to group by date as in the sold/posted screens
                items(itemsForDate) {
                    item -> PurchasedItemCard(item = item)
                }
            }
        }
    }
} 