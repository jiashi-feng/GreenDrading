package com.greendrading.app.ui.publish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DirectSaleInfoViewModel : ViewModel() {
    private val _navigateToItemEntry = MutableLiveData<Boolean>()
    val navigateToItemEntry: LiveData<Boolean> = _navigateToItemEntry

    fun onConfirmDirectSale() {
        _navigateToItemEntry.value = true
    }

    fun onNavigationCompleted() {
        _navigateToItemEntry.value = false
    }
} 