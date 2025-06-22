package com.example.yourapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.greendrading.app.R
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share


// 关注的用户数据类
data class RecommendedUser(
    val id: Int,
    val avatarRes: Int,
    val name: String,
    val followers: Int,
    val posts: Int
)

// 推荐帖子数据类
data class RecommendedPost(
    val id: Int,
    val avatarRes: Int,
    val userName: String,
    val date: String,
    val content: String,
    val imagesRes: List<Int>,
    val views: String,
    val likes: Int,
    val comments: Int,
    val shares: Int
)

@Composable
fun MyFollowScreen(navController: NavController) {
    val recommendedUsers = listOf(
        RecommendedUser(1, R.drawable.ic_plant_1, "汪汪汪汪", 70, 23),
        RecommendedUser(2, R.drawable.ic_plant_2, "喵喵喵喵", 75, 31),
        RecommendedUser(3, R.drawable.ic_plant_3, "咚咚咚咚", 100, 50)
    )

    val recommendedPosts = listOf(
        RecommendedPost(
            id = 1,
            avatarRes = R.drawable.ic_plant_1,
            userName = "胖虎",
            date = "8月21日",
            content = "出郁金香，水培郁金香四季开花盆栽，适合客厅绿植",
            imagesRes = listOf(R.drawable.ic_plant_1, R.drawable.ic_plant_2, R.drawable.ic_plant_3, R.drawable.ic_plant_4),
            views = "3.4万",
            likes = 48,
            comments = 24,
            shares = 4
        )
        // 可以添加更多模拟帖子数据
    )

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF5F5F5))) {
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
            Text("关注", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
            IconButton(
                onClick = { /* TODO: 跳转到个人资料 */ },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(Icons.Filled.Person, contentDescription = "个人", tint = Color.Black)
            }
        }

        // 可滚动内容
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // 搜索框
            OutlinedTextField(
                value = "",
                onValueChange = { /* TODO: 处理搜索输入 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                placeholder = { Text("搜索用户名和点评") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "搜索") },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(0xFFF0F0F0),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            // 关注提示
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color(0xFFE0F7FA),
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Info, contentDescription = "提示", tint = Color(0xFF00BCD4))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("关注这里还是空的哦", color = Color(0xFF00BCD4), modifier = Modifier.weight(1f))
                    IconButton(onClick = { /* TODO: 关闭提示 */ }) {
                        Icon(Icons.Filled.Close, contentDescription = "关闭", tint = Color(0xFF00BCD4))
                    }
                }
            }

            // 你可能感兴趣的用户
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("你可能感兴趣的用户", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                    Text("查看全部 >", color = Color.Gray, fontSize = 14.sp,
                        modifier = Modifier.clickable { /* TODO: 查看全部用户 */ })
                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recommendedUsers) { user ->
                        Card(
                            modifier = Modifier.width(120.dp).height(160.dp),
                            shape = RoundedCornerShape(8.dp),
                            elevation = 2.dp
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = user.avatarRes),
                                    contentDescription = user.name,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(Color.LightGray),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(user.name, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                                Text("粉丝 ${user.followers} 内容 ${user.posts}", fontSize = 12.sp, color = Color.Gray)
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(onClick = { /* TODO: 关注用户 */ }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))) {
                                    Text("关注", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            // 绑定通讯录提示
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color(0xFFF0F8FF),
                elevation = 0.dp
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Filled.Phone, contentDescription = "电话", tint = Color(0xFF4CAF50))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("绑定通讯录，寻找认识的小伙伴", color = Color.Black, modifier = Modifier.weight(1f))
                    Button(onClick = { /* TODO: 立即绑定 */ }, colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50))) {
                        Text("立即绑定", color = Color.White)
                    }
                    IconButton(onClick = { /* TODO: 关闭提示 */ }) {
                        Icon(Icons.Filled.Close, contentDescription = "关闭", tint = Color.Gray)
                    }
                }
            }

            // 为你推荐帖子列表
            Column(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text("为你推荐", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(recommendedPosts) { post ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            elevation = 2.dp
                        ) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = post.avatarRes),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(CircleShape)
                                            .background(Color.LightGray),
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(post.userName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        Text(post.date, fontSize = 12.sp, color = Color.Gray)
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(post.content, fontSize = 14.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text("${post.views} 浏览", fontSize = 12.sp, color = Color.Gray)
                                    Row {
                                        IconButton(onClick = { /* TODO: 点赞 */ }) {
                                            Icon(Icons.Filled.Favorite, contentDescription = "点赞", tint = Color.Gray)
                                        }
                                        Text("${post.likes}", fontSize = 12.sp, color = Color.Gray)
                                        IconButton(onClick = { /* TODO: 评论 */ }) {
                                            Icon(Icons.Filled.Comment, contentDescription = "评论", tint = Color.Gray)
                                        }
                                        Text("${post.comments}", fontSize = 12.sp, color = Color.Gray)
                                        IconButton(onClick = { /* TODO: 分享 */ }) {
                                            Icon(Icons.Filled.Share, contentDescription = "分享", tint = Color.Gray)
                                        }
                                        Text("${post.shares}", fontSize = 12.sp, color = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
} 