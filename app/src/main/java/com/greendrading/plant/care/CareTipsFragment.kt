package com.greendrading.plant.care

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.R

class CareTipsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_care_tips, container, false)

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Setup click listeners for each plant container to navigate to CareDetailFragment
        view.findViewById<View>(R.id.plant1_container).setOnClickListener {
            navigateToCareDetail("多肉植物养护", R.drawable.sell_plant_10, "这里是多肉植物的具体养护技巧内容...", "花盆、土壤、园艺剪、洒水壶等")
        }
        view.findViewById<View>(R.id.plant2_container).setOnClickListener {
            navigateToCareDetail("常见绿植养护", R.drawable.sell_plant_4, "这里是常见绿植的具体养护技巧内容...", "花盆、土壤、园艺剪、洒水壶等")
        }
        view.findViewById<View>(R.id.plant3_container).setOnClickListener {
            navigateToCareDetail("花卉养护技巧", R.drawable.sell_plant_2, "这里是花卉的具体养护技巧内容...", "花盆、土壤、园艺剪、洒水壶等")
        }
        view.findViewById<View>(R.id.plant4_container).setOnClickListener {
            navigateToCareDetail("水培植物养护", R.drawable.sell_plant_1, "这里是水培植物的具体养护技巧内容...", "花盆、土壤、园艺剪、洒水壶等")
        }
        view.findViewById<View>(R.id.plant5_container).setOnClickListener {
            navigateToCareDetail("病虫害防治", R.drawable.sell_plant_3, "这里是病虫害防治的具体养护技巧内容...", "杀虫剂、杀菌剂、喷雾器等")
        }
        view.findViewById<View>(R.id.plant6_container).setOnClickListener {
            navigateToCareDetail("土壤肥料选择", R.drawable.round_plant_3, "这里是土壤肥料选择的具体养护技巧内容...", "测量工具、各种肥料等")
        }

        return view
    }

    private fun navigateToCareDetail(title: String, imageResId: Int, tips: String, tools: String) {
        val bundle = Bundle().apply {
            putString("careTitle", title)
            putInt("careImage", imageResId)
            putString("careTips", tips)
            putString("careTools", tools)
        }
        findNavController().navigate(R.id.action_careTipsFragment_to_careDetailFragment, bundle)
    }
} 