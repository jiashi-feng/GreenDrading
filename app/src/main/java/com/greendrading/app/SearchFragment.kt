package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化搜索页面的逻辑
        val binding = com.greendrading.app.databinding.FragmentSearchBinding.bind(view)
        binding.btnSearch.setOnClickListener {
            findNavController().navigate(R.id.shoppingListFragment)
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }

        // Set up click listener for "多肉" TextView
        view.findViewById<TextView>(R.id.tvDuoRou).setOnClickListener {
            findNavController().navigate(R.id.shoppingListFragment)
        }

        // Set up cancel button click listener
        view.findViewById<TextView>(R.id.btnCancel).setOnClickListener {
            findNavController().navigateUp()
        }
    }
} 