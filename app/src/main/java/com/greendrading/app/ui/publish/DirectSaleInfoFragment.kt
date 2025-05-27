package com.greendrading.app.ui.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.greendrading.app.databinding.FragmentDirectSaleInfoBinding

class DirectSaleInfoFragment : Fragment() {
    private var _binding: FragmentDirectSaleInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DirectSaleInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDirectSaleInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConfirmButton()
        observeViewModel()
    }

    private fun setupConfirmButton() {
        binding.btnConfirmDirectSale.setOnClickListener {
            viewModel.onConfirmDirectSale()
        }
    }

    private fun observeViewModel() {
        viewModel.navigateToItemEntry.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                navigateToItemEntry()
                viewModel.onNavigationCompleted()
            }
        }
    }

    private fun navigateToItemEntry() {
        val action = DirectSaleInfoFragmentDirections
            .actionDirectSaleInfoFragmentToDirectSaleItemEntryFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 