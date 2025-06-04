package com.greendrading.app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.greendrading.app.databinding.FragmentPublishBinding

class PublishFragment : Fragment() {
    private var _binding: FragmentPublishBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPublishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardShare.setOnClickListener {
            startActivity(Intent(requireContext(), ShareGoodFindsActivity::class.java))
        }

        binding.cardQuickSell.setOnClickListener {
            startActivity(Intent(requireContext(), QuickSellActivity::class.java))
        }

        binding.cardNormalSell.setOnClickListener {
            startActivity(Intent(requireContext(), NormalSellActivity::class.java))
        }

        binding.cardConsignment.setOnClickListener {
            startActivity(Intent(requireContext(), ConsignmentActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 