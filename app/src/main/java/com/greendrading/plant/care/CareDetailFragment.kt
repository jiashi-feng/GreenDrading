package com.greendrading.plant.care

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.greendrading.app.R

class CareDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_care_detail, container, false)

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Get data from arguments
        arguments?.let { bundle ->
            val title = bundle.getString("careTitle")
            val imageResId = bundle.getInt("careImage")
            val tips = bundle.getString("careTips")
            val tools = bundle.getString("careTools")

            view.findViewById<TextView>(R.id.careDetailBannerTitle).text = title
            view.findViewById<ImageView>(R.id.plantImage).setImageResource(imageResId)
            view.findViewById<TextView>(R.id.careTipsContent).text = tips
            view.findViewById<TextView>(R.id.toolsContent).text = tools
        }

        val commentInput = view.findViewById<EditText>(R.id.commentInput)
        val submitCommentButton = view.findViewById<Button>(R.id.submitCommentButton)
        val commentsContainer = view.findViewById<LinearLayout>(R.id.commentsContainer)

        submitCommentButton.setOnClickListener {
            val commentText = commentInput.text.toString().trim()
            if (commentText.isNotEmpty()) {
                // Add new comment to the commentsContainer
                val newCommentView = LayoutInflater.from(context).inflate(R.layout.comment_item, commentsContainer, false)
                newCommentView.findViewById<TextView>(R.id.commentUser).text = "新用户"
                newCommentView.findViewById<TextView>(R.id.commentContent).text = commentText
                commentsContainer.addView(newCommentView, 0) // Add to top
                commentInput.setText("") // Clear input
                Toast.makeText(context, "评论已提交", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "评论不能为空", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
} 