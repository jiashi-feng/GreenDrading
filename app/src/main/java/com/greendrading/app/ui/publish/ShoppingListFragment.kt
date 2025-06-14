package com.greendrading.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ShoppingListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shoppinglist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up back button click listener
        view.findViewById<TextView>(R.id.tvBack).setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }
        
        // Set up click listeners for each product item
        setupProductItemClickListeners(view)
    }
    
    private fun setupProductItemClickListeners(view: View) {
        // Get all product items from the layout
        val productItems = listOf(
            view.findViewById<View>(R.id.item1),
            view.findViewById<View>(R.id.item2),
            view.findViewById<View>(R.id.item3),
            view.findViewById<View>(R.id.item4),
            view.findViewById<View>(R.id.item5),
            view.findViewById<View>(R.id.item6)
        )
        
        // Set click listener for each product item
        productItems.forEachIndexed { index, item ->
            item.setOnClickListener {
                // Navigate to product detail page with product ID
                val action = ShoppingListFragmentDirections.actionShoppingListFragmentToProductDetailFragment(
                    productId = "product_${index + 1}"
                )
                findNavController().navigate(action)
            }
        }
    }
}
