package com.greendrading.app.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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

// Data class for the main item to be reviewed
data class ReviewItem(
    val sellerName: String,
    val status: String,
    val imageRes: Int,
    val title: String,
    val specs: String,
    val price: String,
    val quantity: Int,
    val refundStatus: String? = null // e.g., "七天无理由退换"
)

// Data class for recommended products
data class RecommendedProduct(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val price: String,
    val salesCount: String,
    val storeName: String
)

@Composable
fun PendingReviewScreen(navController: NavController) {
    Log.d("PendingReviewScreen", "PendingReviewScreen is being composed")
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
                text = "待评价",
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

        // Main content area - Scrollable
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Take remaining space, allowing bottom bar
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            item { // Order Item Card
                val reviewItem = remember { ReviewItem(
                    sellerName = "鱼缸边的猫",
                    status = "待评价",
                    imageRes = R.drawable.ic_plant_1, // Placeholder
                    title = "郁金香, 水培郁金香四季开花",
                    specs = "规格: 20支",
                    price = "9.99",
                    quantity = 1,
                    refundStatus = "七天无理由退换"
                )}
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
                        // Seller Name and Status
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(id = R.drawable.ic_store), contentDescription = "店铺", tint = Color.Gray, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = reviewItem.sellerName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                            Text(
                                text = reviewItem.status,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF9800) // Orange for pending review
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        // Product Details
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { /* TODO: Navigate to product detail */ },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = reviewItem.imageRes),
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
                                    text = reviewItem.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = reviewItem.specs,
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                reviewItem.refundStatus?.let { status ->
                                    Text(
                                        text = status,
                                        fontSize = 12.sp,
                                        color = Color(0xFF0A7A5A),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFF0A7A5A).copy(alpha = 0.1f))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "¥${reviewItem.price}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = "x${reviewItem.quantity}",
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))

                        // Action Buttons for this item
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedButton(
                                onClick = { /* TODO: Apply for After-Sale */ },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                            ) {
                                Text("申请售后", fontSize = 13.sp)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = { /* TODO: Go to Review */ },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0A7A5A))
                            ) {
                                Text("评价", color = Color.White, fontSize = 13.sp)
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item { // Price Summary
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("商品总价", fontSize = 14.sp, color = Color.Black)
                            Text("¥9.99", fontSize = 14.sp, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("运费", fontSize = 14.sp, color = Color.Black)
                            Text("运费(快递) ¥0.00", fontSize = 14.sp, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Text("实付款", fontSize = 15.sp, color = Color.Black)
                            Text("¥9.99", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFFE44A3A))
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item { // Recipient Info & Order Details
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("收货信息", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("收货人姓名, 81888888888,", fontSize = 14.sp, color = Color.Black)
                        Text("XX省XX市XX区XXXXX路XXXX", fontSize = 14.sp, color = Color.Black)
                        Text("号", fontSize = 14.sp, color = Color.Black)

                        Spacer(modifier = Modifier.height(16.dp))

                        // Order Tracking Details
                        OrderInfoRow("订单编号", "1234567890123456") { /* TODO: Copy */ Text("复制", color = Color(0xFF0A7A5A)) }
                        OrderInfoRow("支付宝交易号", "1234567890123456")
                        OrderInfoRow("创建时间", "2025-03-25 16:35:28")
                        OrderInfoRow("付款时间", "2025-03-25 16:35:28")
                        OrderInfoRow("发货时间", "2025-03-26 16:35:28")
                        OrderInfoRow("成交时间", "2025-03-26 16:35:28")
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }

            item { // You Might Like Section Header
                Text(
                    text = "猜你喜欢",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp, bottom = 8.dp)
                )
            }

            item { // Recommended Products Grid
                val recommendedProducts = remember { listOf(
                    RecommendedProduct(1, R.drawable.ic_plant_2, "【秒杀特价】栀子花6.9一盆！！花期带花苞", "6.99", "10人付款", "店铺名称店铺名称"),
                    RecommendedProduct(2, R.drawable.ic_plant_3, "三角梅绿植，绿叶樱花三角梅，抹茶色渐变樱", "29.99", "10人付款", "店铺名称店铺名称"),
                    RecommendedProduct(3, R.drawable.ic_plant_1, "50颗38个品种风雨兰常年开花300天！", "16.90", "15人付款", "店铺A"),
                    RecommendedProduct(4, R.drawable.ic_plant_4, "室外耐热爆花绿植（粉苞冬红/粉帽子）", "169.00", "5人付款", "店铺B")
                )}
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .heightIn(max = 400.dp) // Adjust as needed for scrollable content
                        .padding(horizontal = 8.dp)
                ) {
                    items(recommendedProducts) {
                            product -> RecommendedProductCard(product = product)
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(80.dp)) } // Spacer for bottom bar
        }

        // Bottom Action Bar
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { /* TODO: More options */ }) {
                    Text("更多", color = Color.Black)
                }
                OutlinedButton(
                    onClick = { /* TODO: Delete Order */ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                ) {
                    Text("删除订单")
                }
                OutlinedButton(
                    onClick = { /* TODO: View Logistics */ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
                ) {
                    Text("查看物流")
                }
                Button(
                    onClick = { /* TODO: Go to Review */ },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE44A3A))
                ) {
                    Text("评价", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun OrderInfoRow(label: String, value: String, action: @Composable (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.width(90.dp) // Fixed width for labels
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = value,
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = androidx.compose.ui.text.style.TextAlign.End // Align text to end
            )
            action?.let { 
                Spacer(modifier = Modifier.width(8.dp))
                it()
            }
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun RecommendedProductCard(product: RecommendedProduct) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier.fillMaxWidth().clickable { /* TODO: Navigate to product detail */ }
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.title,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "¥${product.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFFE44A3A) // Red color
                )
                Text(
                    text = "${product.salesCount}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_store), contentDescription = "店铺", tint = Color.Gray, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = product.storeName,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
} 