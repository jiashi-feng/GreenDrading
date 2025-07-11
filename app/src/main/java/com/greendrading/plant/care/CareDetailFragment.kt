package com.greendrading.plant.care

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
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

/**
 * 植物养护详情Fragment
 * 
 * 模块用途：
 * 该模块用于显示植物养护的详细信息，包括养护技巧、工具使用说明和用户评论功能。
 * 
 * 主要功能：
 * 1. 显示植物养护的标题和图片
 * 2. 展示养护所需的工具列表
 * 3. 提供养护技巧的查看功能
 * 4. 支持用户评论功能
 * 
 * 参数说明：
 * - careTitle: 养护标题
 * - careImage: 养护相关图片资源ID
 * - careTools: 养护所需工具说明
 * 
 * 异常处理：
 * 1. 视图相关异常：
 *    - IllegalStateException: 当视图创建失败时
 *    - NullPointerException: 当视图ID未找到时
 * 2. 资源相关异常：
 *    - Resources.NotFoundException: 当图片资源未找到时
 * 3. 生命周期相关异常：
 *    - IllegalStateException: 当在Fragment销毁后访问视图时
 * 
 * 开发人员：GreenGrading Team
 * 开发时间：2024-03-20
 * 最后修改：2024-03-20
 */
class CareDetailFragment : Fragment() {

    // 多肉植物养护技巧文本
    private val succulentCareTips = "多肉植物养护的关键在于模拟其原生干燥环境，平时应给予充足但柔和的阳光，避免夏季烈日直射以免叶片灼伤。浇水要遵循干透浇透的原则，等土壤完全干燥后再彻底浇透，冬季需减少浇水频率。使用透气性好的颗粒土栽培，并确保环境通风良好，防止根部腐烂。夏季高温时注意遮阴控水，冬季低温则要移入室内保暖。定期清理枯叶、检查植株状态，能让多肉保持健康饱满的形态"
    
    // 绿植养护技巧文本
    private val greenPlantCareTips = "绿植养护要注意光照、浇水、通风和清洁。大多数观叶植物喜欢明亮的散射光，避免阳光直射，但也不能长期放在阴暗处。浇水要见干见湿，等盆土表面2-3厘米干了再浇透，冬季适当减少水量。平时可以经常用湿布擦拭叶片，既能除尘又能帮助光合作用。每月施一次稀释的液体肥，春秋季是生长旺季可适当增加。注意定期检查叶片背面，防止红蜘蛛等病虫害，发现病叶及时摘除。保持环境通风，但避免放在空调直吹或暖气片旁边。"
    
    // 花卉养护技巧文本
    private val flowerCareTips = "花卉养护要掌握光、水、肥、气四要素。喜阳花卉如月季、茉莉需要每天至少6小时直射光，而兰花、红掌等耐阴品种放在明亮散射光处即可。浇水要因花而异，杜鹃、栀子等喜湿花卉保持土壤微潮，多肉、虎皮兰等耐旱品种则要等盆土干透再浇。生长期每2周施一次稀释的液肥，花期前增施磷钾肥。定期修剪残花枯枝，既能保持株型美观，又能促进新芽萌发。注意保持环境通风，冬季防寒保暖，夏季适当遮阴，及时防治红蜘蛛、蚜虫等常见病虫害。换盆最好选在春季，新盆比原盆大5厘米左右为宜。"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 加载布局文件
        val view = inflater.inflate(R.layout.fragment_care_detail, container, false)

        // 设置返回按钮点击事件
        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // 从参数中获取数据并设置到视图
        val careDetailBannerTitleTextView = view.findViewById<TextView>(R.id.careDetailBannerTitle)
        arguments?.let { bundle ->
            val title = bundle.getString("careTitle")
            val imageResId = bundle.getInt("careImage")
            val tools = bundle.getString("careTools")

            careDetailBannerTitleTextView.text = title
            view.findViewById<ImageView>(R.id.plantImage).setImageResource(imageResId)
            view.findViewById<TextView>(R.id.toolsContent).text = tools
        }

        // 设置养护技巧点击事件
        val careTipsContainer = view.findViewById<LinearLayout>(R.id.careTipsContainer)
        val careTipsContent = view.findViewById<TextView>(R.id.careTipsContent)

        // 初始化养护技巧文本
        careTipsContent.text = "点击查看养护技巧详情"
        careTipsContent.movementMethod = null

        // 设置养护技巧容器的点击事件
        careTipsContainer.setOnClickListener {
            val currentTitle = careDetailBannerTitleTextView.text.toString()
            val currentContent = careTipsContent.text.toString()

            // 使用post确保在布局完成后更新文本和滚动方法
            view.post { 
                when (currentTitle) {
                    "多肉植物养护" -> {
                        if (currentContent == "点击查看养护技巧详情") {
                            careTipsContent.text = succulentCareTips
                            careTipsContent.movementMethod = ScrollingMovementMethod()
                        } else {
                            careTipsContent.text = "点击查看养护技巧详情"
                            careTipsContent.movementMethod = null
                        }
                    }
                    "常见绿植养护" -> {
                        if (currentContent == "点击查看养护技巧详情") {
                            careTipsContent.text = greenPlantCareTips
                            careTipsContent.movementMethod = ScrollingMovementMethod()
                        } else {
                            careTipsContent.text = "点击查看养护技巧详情"
                            careTipsContent.movementMethod = null
                        }
                    }
                    "花卉养护技巧" -> {
                        if (currentContent == "点击查看养护技巧详情") {
                            careTipsContent.text = flowerCareTips
                            careTipsContent.movementMethod = ScrollingMovementMethod()
                        } else {
                            careTipsContent.text = "点击查看养护技巧详情"
                            careTipsContent.movementMethod = null
                        }
                    }
                    else -> {
                        // 处理未知的养护类型
                        Toast.makeText(context, "未能匹配养护技巧，当前标题为: $currentTitle", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        // 设置评论功能
        val commentInput = view.findViewById<EditText>(R.id.commentInput)
        val submitCommentButton = view.findViewById<Button>(R.id.submitCommentButton)
        val commentsContainer = view.findViewById<LinearLayout>(R.id.commentsContainer)

        // 设置评论提交按钮点击事件
        submitCommentButton.setOnClickListener {
            val commentText = commentInput.text.toString().trim()
            if (commentText.isNotEmpty()) {
                // 创建并添加新评论到评论容器
                val newCommentView = LayoutInflater.from(context).inflate(R.layout.comment_item, commentsContainer, false)
                newCommentView.findViewById<TextView>(R.id.commentUser).text = "新用户"
                newCommentView.findViewById<TextView>(R.id.commentContent).text = commentText
                commentsContainer.addView(newCommentView, 0) // 添加到顶部
                commentInput.setText("") // 清空输入框
                Toast.makeText(context, "评论已提交", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "评论不能为空", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
} 