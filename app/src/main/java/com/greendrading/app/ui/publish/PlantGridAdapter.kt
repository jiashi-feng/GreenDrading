package com.greendrading.app.ui.publish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.greendrading.app.R

class PlantGridAdapter(
    private val plants: List<Int>,
    private val onPlantClick: (Int) -> Unit
) : RecyclerView.Adapter<PlantGridAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.plant_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plant_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(plants[position])
        holder.itemView.setOnClickListener {
            onPlantClick(position)
        }
    }

    override fun getItemCount() = plants.size
} 