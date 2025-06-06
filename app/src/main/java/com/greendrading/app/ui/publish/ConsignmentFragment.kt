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
    private lateinit var plantsAdapter: PlantsAdapter

    // 定义植物数据类
    data class Plant(
        val imageRes: Int,
        val name: String,
        val category: PlantCategory
    )

    // 定义植物分类枚举
    enum class PlantCategory {
        ALL,
        ORNAMENTAL,
        EDIBLE,
        AQUATIC,
        AROMATIC,
        MEDICINAL,
        INDUSTRIAL,
        HYDROPONIC
    }

    // 模拟植物数据
    private val allPlants = listOf(
        Plant(R.drawable.sell_plant_1, "龟背竹", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.sell_plant_5, "玫瑰", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.sell_plant_7, "郁金香", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.sell_plant_4, "仙人掌", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.sell_plant_8, "竹子", PlantCategory.INDUSTRIAL),
        Plant(R.drawable.sell_plant_9, "百合", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.sell_plant_6, "松树", PlantCategory.INDUSTRIAL),
        Plant(R.drawable.sell_plant_2, "龟背竹", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.sell_plant_3, "龟背竹", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.sell_plant_11, "苹果树", PlantCategory.EDIBLE),
        Plant(R.drawable.sell_plant_10, "多肉", PlantCategory.ORNAMENTAL),
        Plant(R.drawable.round_plant_4, "水果树", PlantCategory.AQUATIC ),
         Plant(R.drawable.ic_plant_1, "香草花", PlantCategory.AROMATIC )
    )

    private var currentPlants = allPlants

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

        categoryChips.setOnCheckedChangeListener { _, checkedId ->
            val filteredPlants = when (checkedId) {
                R.id.chip_search -> allPlants
                R.id.chip_ornamental -> allPlants.filter { it.category == PlantCategory.ORNAMENTAL }
                R.id.chip_edible -> allPlants.filter { it.category == PlantCategory.EDIBLE }
                R.id.chip_aquatic -> allPlants.filter { it.category == PlantCategory.AQUATIC }
                R.id.chip_aromatic -> allPlants.filter { it.category == PlantCategory.AROMATIC }
                R.id.chip_medicinal -> allPlants.filter { it.category == PlantCategory.MEDICINAL }
                R.id.chip_industrial -> allPlants.filter { it.category == PlantCategory.INDUSTRIAL }
                R.id.chip_hydroponic -> allPlants.filter { it.category == PlantCategory.HYDROPONIC }
                else -> allPlants
            }
            currentPlants = filteredPlants
            plantsAdapter.updatePlants(filteredPlants)
        }
    }

    private fun setupPlantsGrid() {
        plantsGrid.layoutManager = GridLayoutManager(requireContext(), 2)
        plantsAdapter = PlantsAdapter(currentPlants)
        plantsGrid.adapter = plantsAdapter
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

// 适配器类
class PlantsAdapter(
    private var plants: List<ConsignmentFragment.Plant>
) : RecyclerView.Adapter<PlantsAdapter.PlantViewHolder>() {

    class PlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: androidx.appcompat.widget.AppCompatImageView = itemView.findViewById(R.id.plant_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant_grid, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val plant = plants[position]
        holder.imageView.setImageResource(plant.imageRes)
    }

    override fun getItemCount() = plants.size

    fun updatePlants(newPlants: List<ConsignmentFragment.Plant>) {
        plants = newPlants
        notifyDataSetChanged()
    }
} 