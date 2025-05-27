package com.greendrading.app.ui.publish

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.greendrading.app.databinding.FragmentSellOwnItemBinding

class SellOwnItemFragment : Fragment() {
    private var _binding: FragmentSellOwnItemBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SellOwnItemViewModel by viewModels()
    private lateinit var productImagesAdapter: MediaPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSellOwnItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProductImagesRecyclerView()
        setupInputListeners()
        setupButtons()
        observeViewModel()
    }

    private fun setupProductImagesRecyclerView() {
        productImagesAdapter = MediaPreviewAdapter(
            onDeleteClick = { position -> viewModel.removeImage(position) }
        )
        binding.rvProductImages.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = productImagesAdapter
        }
    }

    private fun setupInputListeners() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateTitle(s?.toString() ?: "")
            }
        })

        binding.etPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val priceText = s?.toString() ?: ""
                if (priceText.isNotEmpty()) {
                    try {
                        viewModel.updatePrice(priceText.toDouble())
                    } catch (e: NumberFormatException) {
                        binding.etPrice.error = "请输入有效价格"
                    }
                }
            }
        })

        binding.rgLogistics.setOnCheckedChangeListener { _, checkedId ->
            val isUsingPlatformLogistics = checkedId == binding.rbPlatformLogistics.id
            viewModel.updateLogisticsType(isUsingPlatformLogistics)
        }
    }

    private fun setupButtons() {
        binding.btnAddImages.setOnClickListener {
            // TODO: 实现图片选择逻辑
        }

        binding.btnSaveDraft.setOnClickListener {
            saveDraft()
        }

        binding.btnPublish.setOnClickListener {
            publish()
        }
    }

    private fun observeViewModel() {
        viewModel.productImages.observe(viewLifecycleOwner) { images ->
            productImagesAdapter.submitList(images)
        }

        viewModel.isFormValid.observe(viewLifecycleOwner) { isValid ->
            binding.btnPublish.isEnabled = isValid
            binding.btnSaveDraft.isEnabled = isValid
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

    private fun collectContent(): ProductContent {
        return ProductContent(
            title = binding.etTitle.text.toString(),
            price = binding.etPrice.text.toString().toDoubleOrNull() ?: 0.0,
            isUsingPlatformLogistics = binding.rbPlatformLogistics.isChecked
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

data class ProductContent(
    val title: String,
    val price: Double,
    val isUsingPlatformLogistics: Boolean
) 