package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.idle_rpg_project.custom.DataBaseHandler
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener { login() }

//        val db = DataBaseHandler(this)
//        val test = db.readData()
//        Log.e("test", test.toString())
    }

    private fun login() {
        val txtEmail = findViewById<EditText>(R.id.email_edittext).text.toString()
        val txtPass = findViewById<EditText>(R.id.password_edittext).text.toString()
        val data = Usuario(method = "login", username = txtEmail, contrasena = txtPass)

        val usuarioService = UsuarioService()

        usuarioService.post(data) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()
                Toast(this).showCustomToast ("#DD0000","Error to call server request.", this)
            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()
                    Toast(this).showCustomToast ("#DD0000","Incorrect user or password, try again.", this)
                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()
                    Toast(this).showCustomToast ("#3F7B35", "Loading information...", this)

                    val data: Usuario = it.records[0]

                    val db = DBHelper(this, null)
                    db.add(data.id, data.username)

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", data);
                    startActivity(intent)
                }
            }
        }
    }
}