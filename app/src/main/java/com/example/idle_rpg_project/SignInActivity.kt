package com.example.idle_rpg_project

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.idle_rpg_project.services.UsuarioService

@Suppress("DEPRECATION")
class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //title = "KotlinApp"
        //val progressDialog = ProgressDialog(this)
        //progressDialog.setTitle("Loading data")
        //progressDialog.setMessage("Please wait...")
        //progressDialog.show()

//        getUsuarios()
    }

//    fun getUsuarios() {
//        val usuarioService = UsuarioService()
//
//        usuarioService.getAll {
//            if (it == null) {
//                Log.e("Error", "Error al solicitar información")
//            }
//            else {
//                for(item in it.records){
//                    Log.d("User:", item.toString())
//                }
//            }
//        }
//    }
}