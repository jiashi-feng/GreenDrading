package com.greendrading.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.R

// 我的收藏 Fragment，承载 Compose 收藏界面
class MyCollectionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                // Compose 收藏界面，展示收藏列表
                MyCollectionScreen(
                    navController = findNavController(),
                    items = listOf(
                        CollectionItem(1, R.drawable.ic_plant_1, "#三角梅#盆景不干了！实体店含泪清仓", "G派爱旅行", 323),
                        CollectionItem(2, R.drawable.ic_plant_2, "栀子花苗，带盆，一盆有多棵小苗...", "G派爱旅行", 323),
                        CollectionItem(3, R.drawable.ic_plant_3, "8字型S型小叶榕树人参榕盆景室内阳台净化空气花", "G派爱旅行", 323),
                        CollectionItem(4, R.drawable.ic_plant_4, "[浇水就活] 铃兰浓香型花苗多年生盆栽花卉植物", "G派爱旅行", 323),
                        CollectionItem(5, R.drawable.ic_plant_1, "【10棵混色蔷薇苗9.9元包邮】蔷薇花苗爬藤四季", "G派爱旅行", 323),
                        CollectionItem(6, R.drawable.ic_plant_2, "【特价包邮】雪柳鲜枝冬季插花耐寒水培植物干枝", "G派爱旅行", 323),
                        CollectionItem(7, R.drawable.ic_plant_3, "多肉植物组合盆栽，适合办公桌", "G派爱旅行", 323),
                        CollectionItem(8, R.drawable.ic_plant_4, "绿萝盆栽，净化空气，易养活", "G派爱旅行", 323),
                        CollectionItem(9, R.drawable.ic_plant_1, "发财树，寓意美好，家居装饰", "G派爱旅行", 323),
                        CollectionItem(10, R.drawable.ic_plant_2, "文竹盆栽，清新自然，提升格调", "G派爱旅行", 323)
                    )
                )
            }
        }
    }
} 