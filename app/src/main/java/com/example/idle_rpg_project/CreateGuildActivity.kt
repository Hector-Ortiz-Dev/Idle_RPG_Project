package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.idle_rpg_project.models.Gremio
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.GremioService
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

@Suppress("DEPRECATION")
class CreateGuildActivity : AppCompatActivity() {
    var user: Usuario = Usuario()
    private lateinit var txtNameGuild: EditText
    private lateinit var spinnerSize: Spinner
    private lateinit var btnCreateGuild: Button
    private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_guild)

        user = intent.getSerializableExtra("user") as Usuario

        initializeViews()

        btnCreateGuild.setOnClickListener { addGremio() }
        btnBack.setOnClickListener { finish() }
    }

    private fun initializeViews() {
        txtNameGuild = findViewById(R.id.createGuildName)
        spinnerSize = findViewById(R.id.createGuildSize)
        btnCreateGuild = findViewById(R.id.btnCreateGuild)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun addGremio() {
        val data = Gremio(method = "post", nombre = txtNameGuild.text.toString(), cantidad = spinnerSize.getSelectedItem().toString().toInt())

        if(data.nombre.isNullOrEmpty()) {
            Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.text_required_fields)}",this)
            return
        }

        val gremioService = GremioService()

        gremioService.post(data) {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color), "${getString(R.string.error_server_500)}",this)
            }
            else {
                if(it.estatus == 404){
                    Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_add_guild)}", this)
                }
                else {
                    Toast(this).showCustomToast (getString(R.string.success_color),"${getString(R.string.success_loading)}",this)

                    val data: Gremio = it.records[0]
                    addLeader(data.id!!)
                }
            }
        }
    }

    private fun addLeader(idGremio: Int) {
        val data = Usuario(method = "addLeader", idUsuario = user.id, idGremio = idGremio)

        val usuarioService = UsuarioService()

        usuarioService.post(data) {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color), "${getString(R.string.error_server_500)}",this)
            }
            else {
                if(it.estatus == 500){
                    Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_add_guild)}", this)
                }
                else {
                    Toast(this).showCustomToast (getString(R.string.success_color),"${getString(R.string.success_guild_created)}",this)

                    val data: Usuario = it.records[0]
                    finish()
                }
            }
        }
    }
}