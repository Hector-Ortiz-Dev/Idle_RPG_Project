package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.adapters.UsuariosGremioAdapter
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService
import showCustomToast

@Suppress("DEPRECATION")
class GuildActivity : AppCompatActivity() {
    var user: Usuario = Usuario()
    private lateinit var usuariosGremio: ArrayList<Usuario>

    private lateinit var recyclerview: RecyclerView
    private lateinit var btnBack: Button
    private lateinit var btnPendientes: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild)

        user = intent.getSerializableExtra("user") as Usuario

        initializeViews()

        getUsuariosGremio()

        if(user.lider == 0){
            btnPendientes.isGone = true
        }

        btnBack.setOnClickListener { finish() }
    }

    private fun initializeViews() {
        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recyclerview_gremio)
        btnBack = findViewById(R.id.btnBack)
        btnPendientes = findViewById(R.id.btnPendientes)
    }

    fun getUsuariosGremio() {
        val usuarioService = UsuarioService()

        usuarioService.getAll {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_server_500)}", this)
            }
            else {
                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(this)

                usuariosGremio = it.records.filter { x -> x.idGremio == user.idGremio && (x.lider == 1 || x.espera == 0) } as ArrayList<Usuario>

                // This will pass the ArrayList to our Adapter
                val adapter = UsuariosGremioAdapter(this, R.layout.card_users_guild, usuariosGremio)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
            }
        }
    }
}