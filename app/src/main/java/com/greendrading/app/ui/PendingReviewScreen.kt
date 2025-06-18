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

// 待评价商品数据类
data class ReviewItem(
    val sellerName: String, // 卖家名称
    val status: String, // 订单状态，例如 "待评价"
    val imageRes: Int, // 商品图片资源ID
    val title: String, // 商品标题
    val specs: String, // 商品规格
    val price: String, // 商品单价
    val quantity: Int, // 购买数量
    val refundStatus: String? = null // 退款状态，例如 "七天无理由退换"，可空
)

// 推荐商品数据类
data class RecommendedProduct(
    val id: Int, // 商品ID
    val imageRes: Int, // 商品图片资源ID
    val title: String, // 商品标题
    val price: String, // 商品价格
    val salesCount: String, // 销量
    val storeName: String // 店铺名称
)

/**
 * 待评价主界面 Composable，展示待评价商品和推荐商品
 * @param navController 用于导航控制
 */
@Composable
fun PendingReviewScreen(navController: NavController) {
    Log.d("PendingReviewScreen", "PendingReviewScreen is being composed")
    Column(
        modifier = Modifier
            .fillMaxSize() // 填充整个屏幕
            .background(Color(0xFFF5F5F5)) // 设置背景色为浅灰色
    ) {
        // 顶部应用栏 (Top AppBar)
        Box(
            modifier = Modifier
                .fillMaxWidth() // 宽度填充父级
                .height(56.dp) // 高度固定
                .background(Color.White) // 背景色为白色
                .padding(horizontal = 16.dp), // 水平内边距
            contentAlignment = Alignment.Center // 内容居中对齐
        ) {
            // 返回按钮
            IconButton(
                onClick = { navController.navigateUp() }, // 点击返回上一级
                modifier = Modifier.align(Alignment.CenterStart) // 靠左对齐
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "返回", tint = Color.Black) // 返回图标
            }
            // 标题
            Text(
                text = "待评价",
                fontWeight = FontWeight.Bold, // 粗体
                fontSize = 20.sp, // 字体大小
                color = Color.Black // 字体颜色
            )
            // 右侧操作按钮 (搜索和更多)
            Row(modifier = Modifier.align(Alignment.CenterEnd)) { // 靠右对齐
                IconButton(onClick = { /* TODO: Search */ }) {
                    Icon(Icons.Filled.Search, contentDescription = "搜索", tint = Color.Black) // 搜索图标
                }
                IconButton(onClick = { /* TODO: More options */ }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "更多", tint = Color.Black) // 更多选项图标
                }
            }
        }

        // 主内容区域 - 可滚动列表
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth() // 宽度填充父级
                .weight(1f) // 占据剩余空间，允许底部栏存在
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) } // 顶部间距

            item { // 订单商品卡片
                // 示例待评价商品数据
                val reviewItem = remember { ReviewItem(
                    sellerName = "鱼缸边的猫",
                    status = "待评价",
                    imageRes = R.drawable.ic_plant_1, // 占位符图片
                    title = "郁金香, 水培郁金香四季开花",
                    specs = "规格: 20支",
                    price = "9.99",
                    quantity = 1,
                    refundStatus = "七天无理由退换"
                )}
                Card(
                    shape = RoundedCornerShape(8.dp), // 圆角形状
                    elevation = 2.dp, // 阴影
                    modifier = Modifier
                        .fillMaxWidth() // 宽度填充父级
                        .padding(horizontal = 16.dp, vertical = 8.dp) // 内边距
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp) // 卡片内容内边距
                    ) {
                        // 卖家名称和状态行
                        Row(
                            modifier = Modifier.fillMaxWidth(), // 宽度填充父级
                            horizontalArrangement = Arrangement.SpaceBetween, // 水平两端对齐
                            verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
                        ) {
                            // 店铺图标和卖家名称
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(painter = painterResource(id = R.drawable.ic_store), contentDescription = "店铺", tint = Color.Gray, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(4.dp)) // 间距
                                Text(
                                    text = reviewItem.sellerName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                            }
                            // 订单状态文本
                            Text(
                                text = reviewItem.status,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFFF9800) // 橙色表示待评价
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp)) // 间距

                        // 商品详情区域
                        Row(
                            modifier = Modifier
                                .fillMaxWidth() // 宽度填充父级
                                .clickable { /* TODO: Navigate to product detail */ }, // 点击可跳转商品详情
                            verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
                        ) {
                            // 商品图片
                            Image(
                                painter = painterResource(id = reviewItem.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp) // 图片大小
                                    .clip(RoundedCornerShape(8.dp)) // 圆角裁剪
                                    .background(Color.LightGray), // 背景色
                                contentScale = ContentScale.Crop // 裁剪方式
                            )
                            Spacer(modifier = Modifier.width(12.dp)) // 间距
                            // 商品标题、规格和退款状态
                            Column(
                                modifier = Modifier.weight(1f) // 占据剩余宽度
                            ) {
                                Text(
                                    text = reviewItem.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    maxLines = 2, // 最多两行
                                    overflow = TextOverflow.Ellipsis // 超出省略
                                )
                                Spacer(modifier = Modifier.height(4.dp)) // 间距
                                Text(
                                    text = reviewItem.specs,
                                    fontSize = 13.sp,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(4.dp)) // 间距
                                // 退款状态（如果存在）
                                reviewItem.refundStatus?.let { status ->
                                    Text(
                                        text = status,
                                        fontSize = 12.sp,
                                        color = Color(0xFF0A7A5A),
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFF0A7A5A).copy(alpha = 0.1f)) // 背景色和透明度
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp)) // 间距
                            // 商品价格和数量
                            Column(horizontalAlignment = Alignment.End) { // 靠右对齐
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
                        Spacer(modifier = Modifier.height(12.dp)) // 间距

                        // 商品操作按钮区域
                        Row(
                            modifier = Modifier.fillMaxWidth(), // 宽度填充父级
                            horizontalArrangement = Arrangement.End, // 水平靠右对齐
                            verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
                        ) {
                            // 申请售后按钮
                            OutlinedButton(
                                onClick = { /* TODO: Apply for After-Sale */ },
                                shape = RoundedCornerShape(20.dp), // 圆角形状
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black) // 按钮颜色
                            ) {
                                Text("申请售后", fontSize = 13.sp)
                            }
                            Spacer(modifier = Modifier.width(8.dp)) // 间距
                            // 评价按钮
                            Button(
                                onClick = { /* TODO: Go to Review */ },
                                shape = RoundedCornerShape(20.dp), // 圆角形状
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0A7A5A)) // 按钮背景色
                            ) {
                                Text("评价", color = Color.White, fontSize = 13.sp)
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(12.dp)) } // 间距

            item { // 价格汇总区域（示例，可以根据原型图调整或移除）
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        // 运费行
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("运费", fontSize = 14.sp, color = Color(0xFF666666))
                            Text("¥0.00", fontSize = 14.sp, color = Color(0xFF333333))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // 实付金额行
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                            Text("实付金额：", fontSize = 14.sp, color = Color(0xFF333333))
                            Text("¥9.99", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF0A7A5A))
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) } // 间距

            item { // 猜你喜欢区域头部
                Text(
                    text = "猜你喜欢",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(start = 20.dp, top = 8.dp, bottom = 8.dp)
                )
            }

            item { // 推荐商品网格
                // 示例推荐商品数据
                val recommendedProducts = remember { listOf(
                    RecommendedProduct(1, R.drawable.ic_plant_2, "【秒杀特价】栀子花6.9一盆！！花期带花苞", "6.99", "10人付款", "店铺名称店铺名称"),
                    RecommendedProduct(2, R.drawable.ic_plant_3, "三角梅绿植，绿叶樱花三角梅，抹茶色渐变樱", "29.99", "10人付款", "店铺名称店铺名称"),
                    RecommendedProduct(3, R.drawable.ic_plant_1, "50颗38个品种风雨兰常年开花300天！", "16.90", "15人付款", "店铺A"),
                    RecommendedProduct(4, R.drawable.ic_plant_4, "室外耐热爆花绿植（粉苞冬红/粉帽子）", "169.00", "5人付款", "店铺B")
                )}
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // 两列网格
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp), // 内容内边距
                    horizontalArrangement = Arrangement.spacedBy(8.dp), // 水平间距
                    verticalArrangement = Arrangement.spacedBy(8.dp), // 垂直间距
                    modifier = Modifier
                        .heightIn(max = 400.dp) // 最大高度，使其可滚动
                        .padding(horizontal = 8.dp)
                ) {
                    items(recommendedProducts) { product ->
                        RecommendedProductCard(product = product) // 推荐商品卡片
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(80.dp)) } // 底部间距，为底部栏留出空间
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

/**
 * 推荐商品卡片 Composable
 * @param product 推荐商品数据
 */
@Composable
fun RecommendedProductCard(product: RecommendedProduct) {
    Card(
        shape = RoundedCornerShape(8.dp), // 圆角形状
        elevation = 2.dp, // 阴影
        modifier = Modifier
            .fillMaxWidth() // 宽度填充父级
            .clickable { /* TODO: Navigate to product detail */ } // 点击可跳转商品详情
    ) {
        Column {
            // 商品图片
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth() // 宽度填充父级
                    .height(120.dp) // 固定高度
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)) // 顶部圆角裁剪
                    .background(Color.LightGray), // 背景色
                contentScale = ContentScale.Crop // 裁剪方式
            )
            Column(modifier = Modifier.padding(8.dp)) {
                // 商品标题
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 2, // 最多两行
                    overflow = TextOverflow.Ellipsis, // 超出省略
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp)) // 间距
                // 商品价格
                Text(
                    text = "¥${product.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF0A7A5A) // 绿色价格
                )
                Spacer(modifier = Modifier.height(4.dp)) // 间距
                // 销量和店铺名称
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.salesCount,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.ic_store), contentDescription = "店铺", tint = Color.Gray, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(2.dp))
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
    }
} 