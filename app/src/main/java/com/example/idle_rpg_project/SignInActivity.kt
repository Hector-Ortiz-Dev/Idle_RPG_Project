package com.example.idle_rpg_project

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

@Suppress("DEPRECATION")
class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val userImage = findViewById<ImageView>(R.id.user_image)
        val nameInput = findViewById<EditText>(R.id.name_input)
        val lastNameInput = findViewById<EditText>(R.id.last_name_input)
        val usernameInput = findViewById<EditText>(R.id.username_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val confirmPasswordInput = findViewById<EditText>(R.id.confirm_password_input)
        val registerButton = findViewById<Button>(R.id.register_button)
        val loginButton = findViewById<Button>(R.id.login_button)

        registerButton.setOnClickListener { signIn() }
        loginButton.setOnClickListener { finish() }
    }

    private fun signIn() {
        val nameInput = findViewById<EditText>(R.id.name_input).text.toString()
        val lastNameInput = findViewById<EditText>(R.id.last_name_input).text.toString()
        val usernameInput = findViewById<EditText>(R.id.username_input).text.toString()
        val emailInput = findViewById<EditText>(R.id.email_input).text.toString()
        val passwordInput = findViewById<EditText>(R.id.password_input).text.toString()
        val confirmPasswordInput = findViewById<EditText>(R.id.confirm_password_input).text.toString()
        val data = Usuario(method = "post", username = usernameInput, nombre = nameInput, apellidos = lastNameInput, correo = emailInput, contrasena = passwordInput)

        val usuarioService = UsuarioService()

        usuarioService.post(data) {
            if (it == null) {
                Toast(this).showCustomToast (
                    getString(R.string.error_color),
                    "${getString(R.string.error_server_500)}",
                    this)

            }
            else {
                if(it.estatus == 500){
                    Toast(this).showCustomToast (
                        getString(R.string.error_color),
                        "${getString(R.string.error_server_500)}",
                        this)
                }
                else {
                    Toast(this).showCustomToast (
                        getString(R.string.success_color),
                        "${getString(R.string.success_user_created)}",
                        this)

                    val data: Usuario = it.records[0]

                    finish()
                }
            }
        }
    }

}