package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
            ?.findNavController() ?: findNavController()
        (view as ComposeView).setContent {
            com.example.yourapp.ui.MyProfileScreen(navController = navController)
        }
    }
} 