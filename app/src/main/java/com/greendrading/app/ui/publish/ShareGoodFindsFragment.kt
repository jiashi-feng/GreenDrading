package com.greendrading.app.ui.publish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.greendrading.app.databinding.FragmentShareGoodFindsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShareGoodFindsFragment : Fragment() {
    private var _binding: FragmentShareGoodFindsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShareGoodFindsViewModel by viewModels()
    private lateinit var mediaPreviewAdapter: MediaPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShareGoodFindsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMediaPreviewRecyclerView()
        setupTagInput()
        setupButtons()
        observeViewModel()
    }

    private fun setupMediaPreviewRecyclerView() {
        mediaPreviewAdapter = MediaPreviewAdapter(
            onDeleteClick = { position -> viewModel.removeMedia(position) }
        )
        binding.rvMediaPreviews.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = mediaPreviewAdapter
        }
    }

    private fun setupTagInput() {
        binding.etAddTag.setOnEditorActionListener { _, _, _ ->
            val tag = binding.etAddTag.text.toString().trim()
            if (tag.isNotEmpty()) {
                addTag(tag)
                binding.etAddTag.text?.clear()
            }
            true
        }
    }

    private fun addTag(tagText: String) {
        Chip(requireContext()).apply {
            text = "#$tagText"
            isCloseIconVisible = true
            setOnCloseIconClickListener { binding.chipGroupTags.removeView(this) }
            binding.chipGroupTags.addView(this)
        }
    }

    private fun setupButtons() {
        binding.btnAddMedia.setOnClickListener {
            // TODO: 实现媒体选择逻辑
        }

        binding.btnSaveDraft.setOnClickListener {
            saveDraft()
        }

        binding.btnPublish.setOnClickListener {
            publish()
        }
    }

    private fun observeViewModel() {
        viewModel.mediaItems.observe(viewLifecycleOwner) { items ->
            mediaPreviewAdapter.submitList(items)
        }
    }

    private fun saveDraft() {
        val content = collectContent()
        viewModel.saveDraft(content)
    }

    private fun publish() {
        val content = collectContent()
        viewModel.publish(content)
    }

    private fun collectContent(): ShareContent {
        return ShareContent(
            description = binding.etDescription.text.toString(),
            tags = binding.chipGroupTags.children
                .filterIsInstance<Chip>()
                .map { it.text.toString() }
                .toList(),
            isPublic = binding.rbPublic.isChecked
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class ShareContent(
    val description: String,
    val tags: List<String>,
    val isPublic: Boolean
) 