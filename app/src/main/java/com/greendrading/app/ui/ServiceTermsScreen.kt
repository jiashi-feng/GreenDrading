package com.greendrading.app.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import com.greendrading.app.R // 确保R文件导入正确

data class Announcement(val title: String, val learnedCount: Int)

data class Rule(val title: String, val type: RuleType)

enum class RuleType {
    TEXT,
    IMAGE
}

@Composable
fun ServiceTermsScreen(navController: NavController) {
    Log.d("ServiceTermsScreen", "ServiceTermsScreen is being composed")

    val announcements = listOf(
        Announcement("绿韵花坊社区关于助听器商品风险...", 2427),
        Announcement("关于《绿韵花坊社区保证金管理规...", 3109),
        Announcement("关于《绿韵花坊交易超时说明》变...", 2476)
    )

    val rules = listOf(
        Rule("绿韵花坊社区公约", RuleType.TEXT),
        Rule("绿韵花坊社区信息发布规范 (图文版)", RuleType.TEXT),
        Rule("绿韵花坊社区信息发布规范", RuleType.TEXT),
        Rule("绿韵花坊社区管理规范 (试行)", RuleType.TEXT),
        Rule("绿韵花坊卖方软件服务费收费规则", RuleType.TEXT),
        Rule("绿韵花坊用户行为规范", RuleType.TEXT),
        Rule("绿韵花坊交易超时说明", RuleType.TEXT),
        Rule("绿韵花坊社区交易争议处理规范", RuleType.TEXT),
        Rule("绿韵花坊社区\"无房必赔\"服务规范", RuleType.TEXT),
        Rule("绿韵花坊社区\"无票必赔\"服务规范", RuleType.TEXT),
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
                text = "绿韵花坊公约",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }

            // Section: Violation Record
            item {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clickable { /* TODO: Navigate to Violation Record */ }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(painterResource(id = R.drawable.ic_violation_record), contentDescription = "违规记录", tint = Color(0xFFF5B400), modifier = Modifier.size(24.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("违规记录", fontSize = 16.sp, color = Color.Black)
                        }
                        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.Gray)
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section: Latest Announcements
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("最新公告", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF333333))
                    Text("学习全部", fontSize = 14.sp, color = Color(0xFF0A7A5A), modifier = Modifier.clickable { /* TODO: Navigate to All Announcements */ })
                }
                Spacer(modifier = Modifier.height(12.dp))
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(announcements) {
                        AnnouncementCard(it)
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section: User Violation Announcement
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("绿韵花坊用户违规公示", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF333333))
                    Text("查看全部", fontSize = 14.sp, color = Color(0xFF0A7A5A), modifier = Modifier.clickable { /* TODO: Navigate to All User Violations */ })
                }
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "发布违禁信息 用户m****信，于2025-05-10，暂停服务。",
                        fontSize = 14.sp,
                        color = Color(0xFFE53935),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Section: Rules Must Read
            item {
                Text("规则必读", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color(0xFF333333), modifier = Modifier.padding(start = 16.dp))
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Placeholder for image - you might need to add a proper image resource
                                Box(
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(Color.LightGray) // Placeholder background
                                ) {
                                    // If you have an actual image, replace this with Image(painter = painterResource(id = R.drawable.your_image_id))
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text("合规重点风险学习", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("1317456已学习", fontSize = 12.sp, color = Color.Gray)
                                }
                            }
                            Button(
                                onClick = { /* TODO: Learn */ },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0A7A5A)),
                                shape = RoundedCornerShape(4.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Text("学习", color = Color.White, fontSize = 14.sp)
                            }
                        }

                        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))

                        rules.forEach {
                            RuleItem(it)
                            if (it != rules.last()) {
                                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
                            }
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }
    }
}

@Composable
fun AnnouncementCard(announcement: Announcement) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clickable { /* TODO: Navigate to Announcement Detail */ }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(painterResource(id = R.drawable.ic_announcement), contentDescription = "公告", tint = Color(0xFF42A5F5), modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = announcement.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${announcement.learnedCount}已学习",
                fontSize = 10.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun RuleItem(rule: Rule) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Navigate to Rule Detail */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val iconResId = when (rule.type) {
                RuleType.TEXT -> R.drawable.ic_text_rule // Placeholder for text rule icon
                RuleType.IMAGE -> R.drawable.ic_image_rule // Placeholder for image rule icon
            }
            Icon(painterResource(id = iconResId), contentDescription = null, tint = Color(0xFF42A5F5), modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = rule.title, fontSize = 15.sp, color = Color.Black)
        }
        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = "", tint = Color.Gray)
    }
} 