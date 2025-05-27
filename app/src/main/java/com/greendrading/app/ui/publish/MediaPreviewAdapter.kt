package com.greendrading.app.ui.publish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greendrading.app.databinding.ItemMediaPreviewBinding
import com.greendrading.app.model.MediaItem
import com.greendrading.app.model.MediaType

class MediaPreviewAdapter(
    private val onDeleteClick: (Int) -> Unit
) : ListAdapter<MediaItem, MediaPreviewAdapter.MediaViewHolder>(MediaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val binding = ItemMediaPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MediaViewHolder(binding, onDeleteClick)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MediaViewHolder(
        private val binding: ItemMediaPreviewBinding,
        private val onDeleteClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnDelete.setOnClickListener {
                onDeleteClick(adapterPosition)
            }
        }

        fun bind(item: MediaItem) {
            Glide.with(binding.root)
                .load(item.uri)
                .centerCrop()
                .into(binding.ivPreview)

            if (item.type == MediaType.VIDEO) {
                binding.ivVideoIndicator.visibility = android.view.View.VISIBLE
            } else {
                binding.ivVideoIndicator.visibility = android.view.View.GONE
            }
        }
    }
}

private class MediaDiffCallback : DiffUtil.ItemCallback<MediaItem>() {
    override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
        return oldItem == newItem
    }
} 