package com.greendrading.app.ui.publish

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.NormalSellActivity
import com.greendrading.app.R
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
            findNavController().navigate(R.id.action_publishFragment_to_shareGoodFindsFragment)
        }

        binding.cardQuickSell.setOnClickListener {
            findNavController().navigate(R.id.action_publishFragment_to_quickSellFragment)
        }

        binding.cardNormalSell.setOnClickListener {
            startActivity(Intent(requireContext(), NormalSellActivity::class.java))
        }

        binding.cardConsignment.setOnClickListener {
            findNavController().navigate(R.id.action_publishFragment_to_consignmentFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 