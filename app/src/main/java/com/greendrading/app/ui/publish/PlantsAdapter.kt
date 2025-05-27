package com.greendrading.app.ui.publish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greendrading.app.databinding.ItemPlantBinding

class PlantsAdapter(
    private val onPlantClick: (Plant) -> Unit
) : ListAdapter<Plant, PlantsAdapter.PlantViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemPlantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlantViewHolder(binding, onPlantClick)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlantViewHolder(
        private val binding: ItemPlantBinding,
        private val onPlantClick: (Plant) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(plant: Plant) {
            binding.apply {
                tvPlantName.text = plant.name
                tvCategory.text = plant.category
                chipCommissionRate.text = plant.commissionRate

                Glide.with(root)
                    .load(plant.imageUrl)
                    .centerCrop()
                    .into(ivPlant)

                root.setOnClickListener { onPlantClick(plant) }
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
} 