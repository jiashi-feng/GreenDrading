package com.greendrading.app.ui.publish

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PublishTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ShareGoodFindsFragment()
            1 -> SellOwnItemFragment()
            2 -> DirectSaleInfoFragment()
            3 -> ConsignmentSelectionFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
} 