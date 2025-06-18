package com.greendrading.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.greendrading.app.R

// 假设 CollectionItem 是用于历史浏览界面的数据类，与我的收藏界面共享
data class HistoryItem(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val author: String,
    val views: String
)

@Composable
fun MyHistoryScreen(navController: NavController) {
    val historyItems = listOf(
        HistoryItem(1, R.drawable.ic_plant_1, "我的第一盆盆栽", "小明", "120浏览"),
        HistoryItem(2, R.drawable.ic_plant_2, "多肉植物养护心得", "小红", "240浏览"),
        HistoryItem(3, R.drawable.ic_plant_3, "如何挑选健康的绿植", "老王", "300浏览"),
        HistoryItem(4, R.drawable.ic_plant_4, "家庭园艺小技巧", "小李", "150浏览"),
        HistoryItem(5, R.drawable.ic_plant_1, "我的第一盆盆栽", "小明", "120浏览"),
        HistoryItem(6, R.drawable.ic_plant_2, "多肉植物养护心得", "小红", "240浏览"),
        HistoryItem(7, R.drawable.ic_plant_3, "如何挑选健康的绿植", "老王", "300浏览"),
        HistoryItem(8, R.drawable.ic_plant_4, "家庭园艺小技巧", "小李", "150浏览"),
        HistoryItem(9, R.drawable.ic_plant_1, "我的第一盆盆栽", "小明", "120浏览"),
        HistoryItem(10, R.drawable.ic_plant_2, "多肉植物养护心得", "小红", "240浏览"),
        HistoryItem(11, R.drawable.ic_plant_3, "如何挑选健康的绿植", "老王", "300浏览"),
        HistoryItem(12, R.drawable.ic_plant_4, "家庭园艺小技巧", "小李", "150浏览")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // 顶部标题栏
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
            Text("历史浏览", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
            IconButton(
                onClick = { /* TODO: 清空历史记录 */ },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = "更多", tint = Color.Black)
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().weight(1f),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(historyItems) { item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Image(
                            painter = painterResource(id = item.imageRes),
                            contentDescription = item.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            item.title,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            item.author,
                            fontSize = 12.sp,
                            color = Color.Gray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            item.views,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
} 