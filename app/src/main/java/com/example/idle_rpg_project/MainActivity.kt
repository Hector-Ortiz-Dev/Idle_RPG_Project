package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.idle_rpg_project.models.Jugador
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.JugadorService
import showCustomToast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var player = Jugador()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val user = intent.getSerializableExtra("user") as Usuario
        val id = user.id
        getJugador(id!!)
    }

    fun getJugador(id: Int) {
        val jugadorService = JugadorService()
        jugadorService.getById(id) {
            if (it == null) {
                Toast(this).showCustomToast ("#DD0000","Error to call server request.", this)
            }
            else {
                if(it.estatus == 404){
                    Toast(this).showCustomToast ("#DD0000","Incorrect user or password, try again.", this)
                }
                else{
                    player = it.records[0]

                    val imgHead = findViewById<ImageView>(R.id.cabeza)
                    val imgTorso = findViewById<ImageView>(R.id.torso)
                    val imgBrazoDer = findViewById<ImageView>(R.id.brazoDer)
                    val imgBrazoIzq = findViewById<ImageView>(R.id.brazoIzq)
                    val imgPieDer = findViewById<ImageView>(R.id.pieDer)
                    val imgPieIzq = findViewById<ImageView>(R.id.pieIzq)

                    val mediaHead = "https://movilesmx.000webhostapp.com/idle_rpg/images/jugador/head/${player.cabeza}/${player.cabezaC}.png"
                    val mediaTorso = "https://movilesmx.000webhostapp.com/idle_rpg/images/jugador/body/${player.torso}.png"
                    val mediaBrazoDer = "https://movilesmx.000webhostapp.com/idle_rpg/images/jugador/right_arm/${player.brazoDer}/${player.brazoDerC}.png"
                    val mediaBrazoIzq = "https://movilesmx.000webhostapp.com/idle_rpg/images/jugador/left_arm/${player.brazoIzq}/${player.brazoIzqC}.png"
                    val mediaPieDer = "https://movilesmx.000webhostapp.com/idle_rpg/images/jugador/right_leg/${player.pieDer}/${player.pieDerC}.png"
                    val mediaPieIzq = "https://movilesmx.000webhostapp.com/idle_rpg/images/jugador/left_leg/${player.pieIzq}/${player.pieIzqC}.png"

                    Glide.with(this).load(mediaHead).into(imgHead)
                    Glide.with(this).load(mediaTorso).into(imgTorso)
                    Glide.with(this).load(mediaBrazoDer).into(imgBrazoDer)
                    Glide.with(this).load(mediaBrazoIzq).into(imgBrazoIzq)
                    Glide.with(this).load(mediaPieDer).into(imgPieDer)
                    Glide.with(this).load(mediaPieIzq).into(imgPieIzq)
                }
            }
        }
    }
}