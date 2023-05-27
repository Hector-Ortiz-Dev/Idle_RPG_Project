package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.idle_rpg_project.models.Gremio
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

@Suppress("DEPRECATION")
class EditProfileActivity : AppCompatActivity() {
    var user: Usuario = Usuario()
    var gremio: Gremio = Gremio()

    private lateinit var usernameInput: EditText
    private lateinit var nombreInput: EditText
    private lateinit var apellidosInput: EditText
    private lateinit var btnConfirm: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        user = intent.getSerializableExtra("user") as Usuario

        initializeViews()

        usernameInput.setText(user.username)
        nombreInput.setText(user.nombre)
        apellidosInput.setText(user.apellidos)

        btnConfirm.setOnClickListener { editProfile() }
    }

    private fun initializeViews() {
        usernameInput = findViewById(R.id.username_input)
        nombreInput = findViewById(R.id.nombre_input)
        apellidosInput = findViewById(R.id.apellidos_input)
        btnConfirm = findViewById(R.id.register_button)
    }

    private fun editProfile() {
        val data = Usuario(
            method = "put",
            id = user.id,
            username = usernameInput.text.toString(),
            nombre = nombreInput.text.toString(),
            apellidos = apellidosInput.text.toString()
        )

        if(data.username.isNullOrEmpty() ||
            data.nombre.isNullOrEmpty() ||
            data.apellidos.isNullOrEmpty()) {
            Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.text_required_fields)}",this)
            return
        }

        val usuarioService = UsuarioService()

        usuarioService.post(data) {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color), "${getString(R.string.error_server_500)}", this)

            }
            else {
                if(it.estatus == 404){
                    Toast(this).showCustomToast (getString(R.string.error_color), "${getString(R.string.error_404)}", this)
                }
                else {
                    Toast(this).showCustomToast (getString(R.string.success_color), "${getString(R.string.success_loading)}", this)

                    val data: Usuario = it.records[0]

                    finish()
                }
            }
        }
    }
}