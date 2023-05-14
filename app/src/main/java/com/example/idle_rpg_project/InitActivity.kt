package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView

class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_acticity)

        val logo = findViewById<ImageView>(R.id.initLogo)

        //val animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in)
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        //val animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        logo.startAnimation(animationFadeIn)
        //logo.startAnimation(animationFadeOut)
    }
}