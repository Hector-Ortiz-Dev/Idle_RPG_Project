package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = intent.getSerializableExtra("user")

        Log.e("User", user.toString())
    }
}