package com.greendrading.app.ui.publish

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greendrading.app.model.MediaItem
import com.greendrading.app.model.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellOwnItemViewModel @Inject constructor() : ViewModel() {
    private val _productImages = MutableLiveData<List<MediaItem>>(emptyList())
    val productImages: LiveData<List<MediaItem>> = _productImages

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> = _isFormValid

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    private var title: String = ""
    private var price: Double = 0.0
    private var isUsingPlatformLogistics: Boolean = false

    fun addImage(uri: Uri) {
        val currentList = _productImages.value.orEmpty().toMutableList()
        if (currentList.size >= 6) {
            _toastMessage.value = "最多只能上传6张图片"
            return
        }
        currentList.add(MediaItem(uri))
        _productImages.value = currentList
        validateForm()
    }

    fun removeImage(position: Int) {
        val currentList = _productImages.value.orEmpty().toMutableList()
        if (position in currentList.indices) {
            currentList.removeAt(position)
            _productImages.value = currentList
            validateForm()
        }
    }

    fun updateTitle(newTitle: String) {
        title = newTitle
        validateForm()
    }

    fun updatePrice(newPrice: Double) {
        price = newPrice
        validateForm()
    }

    fun updateLogisticsType(isPlatformLogistics: Boolean) {
        isUsingPlatformLogistics = isPlatformLogistics
        validateForm()
    }

    private fun validateForm() {
        val hasImages = _productImages.value?.isNotEmpty() == true
        val isTitleValid = title.isNotBlank()
        val isPriceValid = price > 0

        _isFormValid.value = hasImages && isTitleValid && isPriceValid
    }

    fun saveDraft(content: ProductContent) {
        viewModelScope.launch {
            try {
                // TODO: 实现保存草稿逻辑
                // 1. 保存图片到本地存储
                // 2. 保存商品信息到本地数据库
                _toastMessage.value = "草稿保存成功"
            } catch (e: Exception) {
                _toastMessage.value = "保存失败：${e.message}"
            }
        }
    }

    fun publish(content: ProductContent) {
        viewModelScope.launch {
            try {
                // TODO: 实现发布逻辑
                // 1. 上传图片到服务器
                // 2. 发送商品信息到服务器
                // 3. 处理服务器响应
                _toastMessage.value = "发布成功"
            } catch (e: Exception) {
                _toastMessage.value = "发布失败：${e.message}"
            }
        }
    }
} 