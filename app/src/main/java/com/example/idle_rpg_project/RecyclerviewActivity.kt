package com.example.idle_rpg_project

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.models.ItemsViewModel
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService

class RecyclerviewActivity : AppCompatActivity() {
    var usuarios: ArrayList<Usuario> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        // getUsuarios()

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..5) {
            data.add(ItemsViewModel(R.drawable.guild, "Item $i"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(this, data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
    }

    fun getUsuarios() {
        val usuarioService = UsuarioService()

        usuarioService.getAll {
            if (it == null) {
                Log.e("Error", "Error al solicitar información")
            }
            else {
//                // getting the recyclerview by its id
//                val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
//
//                // this creates a vertical layout Manager
//                recyclerview.layoutManager = LinearLayoutManager(this)
//
//                // This will pass the ArrayList to our Adapter
//                val adapter = CustomAdapter(this, it.records)
//
//                // Setting the Adapter with the recyclerview
//                recyclerview.adapter = adapter
            }
        }
    }
}