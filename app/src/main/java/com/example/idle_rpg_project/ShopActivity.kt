package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText

class ShopActivity : AppCompatActivity() {
    private lateinit var btnPocion1: Button
    private lateinit var btnPocion2: Button
    private lateinit var btnPocion3: Button
    private lateinit var btnPocion4: Button
    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        initializeViews()

        btnPocion1.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnPocion1.startAnimation(anim)
        }
        btnPocion2.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnPocion2.startAnimation(anim)
        }
        btnPocion3.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnPocion3.startAnimation(anim)
        }
        btnPocion4.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnPocion4.startAnimation(anim)
        }
        btnBack.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnBack.startAnimation(anim)
            finish()
        }
    }

    private fun initializeViews() {
        btnPocion1 = findViewById(R.id.potion1BuyButton)
        btnPocion2 = findViewById(R.id.potion2BuyButton)
        btnPocion3 = findViewById(R.id.potion3BuyButton)
        btnPocion4 = findViewById(R.id.potion4BuyButton)
        btnBack = findViewById(R.id.btnBack)
    }
}