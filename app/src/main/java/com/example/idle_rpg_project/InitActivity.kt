package com.example.idle_rpg_project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

@Suppress("DEPRECATION")
class InitActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init_acticity)

        val logo = findViewById<ImageView>(R.id.initLogo)

        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade)
        logo.startAnimation(animationFadeIn)

        val db = DBHelper(this, null)
        val cursor = db.get()

        if (cursor!!.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex(DBHelper.ID_COL))
            getUserById(id)
        }
        else {
            Handler().postDelayed({
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }, 500)
        }


        //val username = cursor.getString(cursor.getColumnIndex(DBHelper.USERNAME_COL))

//        Handler().postDelayed({
//            val login = Intent(this, LoginActivity::class.java)
//            startActivity(login)
//        }, 4000)
    }

    private fun getUserById(id: Int) {

        val usuarioService = UsuarioService()

        usuarioService.getById(id) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()
                //Toast(this).showCustomToast ("#DD0000","Error to call server request.", this)
                Handler().postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }, 500)
            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()
                    //Toast(this).showCustomToast ("#DD0000","User not found, try again.", this)
                    Handler().postDelayed({
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }, 500)
                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()
                    //Toast(this).showCustomToast ("#3F7B35", "Loading information...", this)
                    val data: Usuario = it.records[0]

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user", data)
                    startActivity(intent)
                }
            }
        }
    }
}