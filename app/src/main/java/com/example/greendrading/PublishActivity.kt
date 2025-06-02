package com.example.greendrading

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class PublishActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var cardShare: CardView
    private lateinit var cardQuickSell: CardView
    private lateinit var cardNormalSell: CardView
    private lateinit var cardConsignment: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publish)

        // 初始化视图
        initViews()
        // 设置点击监听器
        setClickListeners()
    }

    private fun initViews() {
        cardShare = findViewById(R.id.card_share)
        cardQuickSell = findViewById(R.id.card_quick_sell)
        cardNormalSell = findViewById(R.id.card_normal_sell)
        cardConsignment = findViewById(R.id.card_consignment)
    }

    private fun setClickListeners() {
        cardShare.setOnClickListener(this)
        cardQuickSell.setOnClickListener(this)
        cardNormalSell.setOnClickListener(this)
        cardConsignment.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val intent = when (view.id) {
            R.id.card_share -> Intent(this, ShareGoodFindsActivity::class.java)
            R.id.card_quick_sell -> Intent(this, QuickSellActivity::class.java)
            R.id.card_normal_sell -> Intent(this, NormalSellActivity::class.java)
            R.id.card_consignment -> Intent(this, ConsignmentActivity::class.java)
            else -> return
        }
        startActivity(intent)
    }
} 