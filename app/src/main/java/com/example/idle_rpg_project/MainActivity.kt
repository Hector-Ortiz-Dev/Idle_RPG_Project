package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.idle_rpg_project.custom.DataBaseHandler
import com.example.idle_rpg_project.models.Usuario

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = intent.getSerializableExtra("user")

        //val db = DataBaseHandler(this)
        //db.insertData(user as Usuario)
        //val test = db.readData()
        //Log.e("User", test.toString())

    //Log.e("User", user.toString())
    }
}