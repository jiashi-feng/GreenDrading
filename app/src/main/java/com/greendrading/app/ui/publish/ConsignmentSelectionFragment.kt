package com.greendrading.app.ui.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.greendrading.app.databinding.FragmentConsignmentSelectionBinding

class ConsignmentSelectionFragment : Fragment() {
    private var _binding: FragmentConsignmentSelectionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ConsignmentSelectionViewModel by viewModels()
    private lateinit var plantsAdapter: PlantsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsignmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearch()
        setupCategoryChips()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        plantsAdapter = PlantsAdapter { plant ->
            // TODO: 处理植物选择，导航到详情页
        }
        binding.rvPlants.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = plantsAdapter
        }
    }

    private fun setupSearch() {
        binding.etSearch.doAfterTextChanged { text ->
            viewModel.search(text?.toString() ?: "")
        }
    }

    private fun setupCategoryChips() {
        binding.chipGroupCategories.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChip = checkedIds.firstOrNull()?.let { group.findViewById<Chip>(it) }
            val category = selectedChip?.text?.toString()
            viewModel.filterByCategory(category)
        }
    }

    private fun observeViewModel() {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            plantsAdapter.submitList(plants)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 