package com.example.yourapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.R

class MyHistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyHistoryScreen(
                    navController = findNavController(),
                    items = listOf(
                        CollectionItem(1, R.drawable.ic_plant_4, "养护要点：掌握这些，让你的绿植四季常青！", "自然之声", 123),
                        CollectionItem(2, R.drawable.ic_plant_3, "新手必看：如何选择适合你的第一盆绿植？", "园艺小站", 245),
                        CollectionItem(3, R.drawable.ic_plant_2, "花卉摄影技巧：拍出植物最美的一面", "镜头下的花草", 188),
                        CollectionItem(4, R.drawable.ic_plant_1, "室内植物搭配指南：打造你的绿色小天地", "家有绿意", 301),
                        CollectionItem(5, R.drawable.ic_plant_4, "多肉植物养护进阶：让你的肉肉更肥美", "多肉星球", 99),
                        CollectionItem(6, R.drawable.ic_plant_3, "盆景艺术：微缩景观中的诗意生活", "盆景之家", 210),
                        CollectionItem(7, R.drawable.ic_plant_2, "水培植物：简单易上手，桌面绿化的好选择", "水生之美", 155),
                        CollectionItem(8, R.drawable.ic_plant_1, "香草植物：不仅能观赏，还能点缀你的生活", "香草花园", 78)
                    )
                )
            }
        }
    }
} 