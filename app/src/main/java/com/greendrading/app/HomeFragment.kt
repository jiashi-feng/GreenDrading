package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        view.findViewById<View>(R.id.searchButton).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }

        view.findViewById<View>(R.id.btnMoreCategory).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_homeCategoryFragment)
        }
    }
} 