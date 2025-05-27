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
class ShareGoodFindsViewModel @Inject constructor() : ViewModel() {
    private val _mediaItems = MutableLiveData<List<MediaItem>>(emptyList())
    val mediaItems: LiveData<List<MediaItem>> = _mediaItems

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> = _toastMessage

    fun addMedia(uri: Uri, type: MediaType = MediaType.IMAGE) {
        val currentList = _mediaItems.value.orEmpty().toMutableList()
        if (currentList.size >= 9) {
            _toastMessage.value = "最多只能上传9个媒体文件"
            return
        }
        currentList.add(MediaItem(uri, type))
        _mediaItems.value = currentList
    }

    fun removeMedia(position: Int) {
        val currentList = _mediaItems.value.orEmpty().toMutableList()
        if (position in currentList.indices) {
            currentList.removeAt(position)
            _mediaItems.value = currentList
        }
    }

    fun saveDraft(content: ShareContent) {
        viewModelScope.launch {
            try {
                // TODO: 实现保存草稿逻辑
                _toastMessage.value = "草稿保存成功"
            } catch (e: Exception) {
                _toastMessage.value = "保存失败：${e.message}"
            }
        }
    }

    fun publish(content: ShareContent) {
        viewModelScope.launch {
            try {
                // TODO: 实现发布逻辑
                _toastMessage.value = "发布成功"
            } catch (e: Exception) {
                _toastMessage.value = "发布失败：${e.message}"
            }
        }
    }
} 