package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.idle_rpg_project.services.UsuarioService

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        getUsuarios()
    }

    fun getUsuarios() {
        val usuarioService = UsuarioService()
        usuarioService.getAll {
            if (it == null) {
                Log.e("Error", "Error al solicitar información")
            }
            else {
                for(item in it.records){
                    Log.d("User:", item.toString())
                }
            }
        }
    }
}