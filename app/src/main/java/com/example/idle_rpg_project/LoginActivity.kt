package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener { onClickLoginButton() }
    }


    fun onClickLoginButton() {
        val txtEmail = findViewById<EditText>(R.id.email_edittext).text.toString()
        val txtPass = findViewById<EditText>(R.id.password_edittext).text.toString()

        val data = Usuario(method = "login", username = txtEmail, contrasena = txtPass)

        login(data)
    }

    fun login(data: Usuario) {
        val usuarioService = UsuarioService()

        usuarioService.login(data) {
            if (it == null) {
                Toast.makeText(applicationContext, "error to call server request.", Toast.LENGTH_SHORT).show()
                //Log.e("Error", "Error al solicitar información")
            }
            else {
                //Log.e("Error", "Success")
                if(it.estatus == 404){
                    Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}