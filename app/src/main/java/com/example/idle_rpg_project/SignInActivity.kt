package com.example.idle_rpg_project

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.idle_rpg_project.services.UsuarioService

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
        val selectImageButton = findViewById<Button>(R.id.select_image_button)
        val registerButton = findViewById<Button>(R.id.register_button)
        val loginButton = findViewById<Button>(R.id.login_button)

        registerButton.setOnClickListener { signIn() }
        loginButton.setOnClickListener { finish() }
        selectImageButton.setOnClickListener {  }
    }



    private fun signIn(){
        val nameInput = findViewById<EditText>(R.id.name_input).text.toString()
        val lastNameInput = findViewById<EditText>(R.id.last_name_input).text.toString()
        val usernameInput = findViewById<EditText>(R.id.username_input).text.toString()
        val emailInput = findViewById<EditText>(R.id.email_input).text.toString()
        val passwordInput = findViewById<EditText>(R.id.password_input).text.toString()
        val confirmPasswordInput = findViewById<EditText>(R.id.confirm_password_input).text.toString()

//        val intent = Intent(this, CharCreationActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}