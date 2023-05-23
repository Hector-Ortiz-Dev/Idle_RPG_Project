package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.register_button)

        loginButton.setOnClickListener { login() }
    }

    private fun login() {
        val txtEmail = findViewById<EditText>(R.id.email_edittext).text.toString()
        val txtPass = findViewById<EditText>(R.id.password_edittext).text.toString()
        val data = Usuario(method = "login", username = txtEmail, contrasena = txtPass)

        val usuarioService = UsuarioService()

        usuarioService.post(data) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()
                Toast(this).showCustomToast (
                    getString(R.string.error_color),
                    "${getString(R.string.error_server_500)}",
                    this)

            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()
                    Toast(this).showCustomToast (
                        getString(R.string.error_color),
                        "${getString(R.string.error_login_404)}",
                        this)
                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()
                    Toast(this).showCustomToast (
                        getString(R.string.success_color),
                        "${getString(R.string.success_loading)}",
                        this)

                    val data: Usuario = it.records[0]

                    val db = DBHelper(this, null)
                    db.delete()
                    db.add(data.id, data.username)

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", data);
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.exit) //R.string.dialogTitle
        //set message for alert dialog
        builder.setMessage(R.string.sure_exit)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton(R.string.yes){ _, _ ->
            finish()
        }
        //performing negative action
        builder.setNegativeButton(R.string.no){ _, _ ->

        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun register() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}