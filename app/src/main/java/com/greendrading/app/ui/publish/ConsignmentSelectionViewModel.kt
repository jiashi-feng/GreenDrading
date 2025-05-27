package com.greendrading.app.ui.publish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ConsignmentSelectionViewModel : ViewModel() {
    private val _plants = MutableLiveData<List<Plant>>()
    val plants: LiveData<List<Plant>> = _plants

    private var currentSearchQuery: String = ""
    private var currentCategory: String? = null

    init {
        loadPlants()
    }

    private fun loadPlants() {
        viewModelScope.launch {
            // TODO: 从服务器加载植物列表
            // 这里使用模拟数据
            val mockPlants = listOf(
                Plant(
                    id = "1",
                    name = "龟背竹",
                    category = "观赏植物",
                    imageUrl = "https://example.com/monstera.jpg",
                    commissionRate = "佣金5%"
                ),
                Plant(
                    id = "2",
                    name = "薄荷",
                    category = "香草植物",
                    imageUrl = "https://example.com/mint.jpg",
                    commissionRate = "佣金3%"
                )
            )
            _plants.value = mockPlants
        }
    }

    fun search(query: String) {
        currentSearchQuery = query
        filterPlants()
    }

    fun filterByCategory(category: String?) {
        currentCategory = category
        filterPlants()
    }

    private fun filterPlants() {
        viewModelScope.launch {
            // TODO: 实现实际的搜索和过滤逻辑
            // 这里使用模拟数据进行过滤
            val allPlants = _plants.value ?: return@launch
            val filteredPlants = allPlants.filter { plant ->
                val matchesSearch = currentSearchQuery.isEmpty() ||
                        plant.name.contains(currentSearchQuery, ignoreCase = true)
                val matchesCategory = currentCategory == null ||
                        plant.category == currentCategory
                matchesSearch && matchesCategory
            }
            _plants.value = filteredPlants
        }
    }
}

data class Plant(
    val id: String,
    val name: String,
    val category: String,
    val imageUrl: String,
    val commissionRate: String
) 