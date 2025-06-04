package com.greendrading.app.ui.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.ChipGroup
import com.greendrading.app.R

class ConsignmentFragment : Fragment() {

    private lateinit var btnBack: ImageButton
    private lateinit var categoryChips: ChipGroup
    private lateinit var plantsGrid: RecyclerView

    private val plantImages = listOf(
        R.drawable.sell_plant_1,
        R.drawable.sell_plant_2,
        R.drawable.sell_plant_3,
        R.drawable.sell_plant_4,
        R.drawable.sell_plant_5,
        R.drawable.sell_plant_6,
        R.drawable.sell_plant_7,
        R.drawable.sell_plant_8,
        R.drawable.sell_plant_9,
        R.drawable.sell_plant_10,
        R.drawable.sell_plant_11
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_consignment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        setupListeners()
        setupPlantsGrid()
    }

    private fun initViews(view: View) {
        btnBack = view.findViewById(R.id.btn_back)
        categoryChips = view.findViewById(R.id.category_chips)
        plantsGrid = view.findViewById(R.id.plants_grid)
    }

    private fun setupListeners() {
        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        categoryChips.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.chip_search -> showToast("分类检索")
                R.id.chip_ornamental -> showToast("观赏植物")
                R.id.chip_edible -> showToast("食用植物")
                R.id.chip_aquatic -> showToast("水果植物")
                R.id.chip_aromatic -> showToast("香草植物")
                R.id.chip_medicinal -> showToast("药用植物")
                R.id.chip_industrial -> showToast("工业植物")
                R.id.chip_hydroponic -> showToast("水培植物")
            }
        }
    }

    private fun setupPlantsGrid() {
        plantsGrid.layoutManager = GridLayoutManager(requireContext(), 2)
        val adapter = PlantGridAdapter(plantImages) { position ->
            showToast("选择了第${position + 1}个植物")
        }
        plantsGrid.adapter = adapter
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
} 