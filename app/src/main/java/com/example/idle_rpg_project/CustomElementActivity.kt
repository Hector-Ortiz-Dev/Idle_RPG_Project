package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class CustomElementActivity : AppCompatActivity() {
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_element)

        title = "KotlinApp"
        spinner = findViewById(R.id.spinner)
        val superHero = arrayOf<String?>("Batman", "SuperMan", "Flash", "AquaMan", "Shazam")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.spinner_list, superHero)
        arrayAdapter.setDropDownViewResource(R.layout.spinner_list)
        spinner.adapter = arrayAdapter
    }
}