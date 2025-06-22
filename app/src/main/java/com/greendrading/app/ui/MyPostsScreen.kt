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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.greendrading.app.R // Make sure this import is correct for your R file

// Data class for a Post Item
data class Post(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val description: String?, // Optional description
    val price: String,
    val date: String // e.g., "2025-03-24"
)

@Composable
fun PostItem(post: Post) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
            .clickable { /* TODO: Navigate to post detail */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = post.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = post.title,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (post.description != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = post.description,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "¥${post.price}",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
fun MyPostsScreen(navController: NavController) {
    Log.d("MyPostsScreen", "MyPostsScreen is being composed")
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
                text = "我发布的",
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

        // Post List
        val posts = remember { listOf(
            Post(1, R.drawable.ic_plant_1, "50颗38个品种风雨兰常年开花300天！", "生存能力强", "16.90", "2025-03-24"),
            Post(2, R.drawable.ic_plant_2, "【特价2天】发财树盆栽发财树苗室内", "客厅桌面四季常青绿植花卉盆景", "10.80", "2025-03-24"),
            Post(3, R.drawable.ic_plant_3, "东北玲兰100颗【特价】铃兰花苗带芽网", "红手捧花植物小型浓香花卉四季开花", "16.90", "2025-03-24"),
            Post(4, R.drawable.ic_plant_4, "室外耐热爆花绿植（粉苞冬红/粉帽子）", "基地直发盆苗", "169.00", "2025-03-24")
        )}

        // Group posts by date
        val groupedPosts = posts.groupBy { it.date }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            groupedPosts.forEach { (date, postsForDate) ->
                item { // Date Header
                    Text(
                        text = date,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
                items(postsForDate) {
                    post -> PostItem(post = post)
                }
            }
        }
    }
} 