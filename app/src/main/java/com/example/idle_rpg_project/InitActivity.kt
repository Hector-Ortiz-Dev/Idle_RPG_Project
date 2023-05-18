package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

@Suppress("DEPRECATION")
class InitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_acticity)

        val logo = findViewById<ImageView>(R.id.initLogo)

        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade)
        logo.startAnimation(animationFadeIn)

        Handler().postDelayed({
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
        }, 4000)
    }
}