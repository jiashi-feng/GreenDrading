package com.greendrading.app

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.databinding.FragmentHomeCategoryBinding

class HomeCategoryFragment : Fragment() {
    private var _binding: FragmentHomeCategoryBinding? = null
    private val binding get() = _binding!!

    private var currentSelectedCategoryView: TextView? = null

    // List of all content LinearLayouts on the right side
    private lateinit var contentPanels: List<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize content panels
        contentPanels = listOf(
            binding.contentObservationalPlants,
            binding.contentEdiblePlants,
            binding.contentMedicinalPlants,
            binding.contentEconomicCrops,
            binding.contentEcologicalRestoration,
            binding.contentReligiousCultural,
            binding.contentTools
        )

        // Set default selected "观赏植物"
        currentSelectedCategoryView = binding.categoryObservational
        currentSelectedCategoryView?.setBackgroundColor(Color.WHITE)
        currentSelectedCategoryView?.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_green))

        // Set all content panels to GONE except the first one
        contentPanels.forEach { it.visibility = View.GONE }
        binding.contentObservationalPlants.visibility = View.VISIBLE // Show default content

        // Set back button click event
        binding.backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack(null, 0)
        }

        // Setup primary category click events
        setupCategoryClickListeners()
    }

    private fun setupCategoryClickListeners() {
        val categories = listOf(
            binding.categoryObservational to binding.contentObservationalPlants,
            binding.categoryEdible to binding.contentEdiblePlants,
            binding.categoryMedicinal to binding.contentMedicinalPlants,
            binding.categoryEconomic to binding.contentEconomicCrops,
            binding.categoryEcological to binding.contentEcologicalRestoration,
            binding.categoryCultural to binding.contentReligiousCultural,
            binding.categoryTools to binding.contentTools
        )

        for ((categoryView, contentPanel) in categories) {
            categoryView.setOnClickListener {
                // Reset previous selection
                currentSelectedCategoryView?.setBackgroundColor(Color.parseColor("#F8F8F8")) // Default gray background
                currentSelectedCategoryView?.setTextColor(Color.parseColor("#333333")) // Default black text

                // Set current selected state
                it.setBackgroundColor(Color.WHITE) // White background for selected
                (it as TextView).setTextColor(ContextCompat.getColor(requireContext(), R.color.primary_green)) // Green text for selected
                currentSelectedCategoryView = it

                // Hide all content panels
                contentPanels.forEach { panel -> panel.visibility = View.GONE }

                // Show the corresponding content panel
                contentPanel.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 