package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.idle_rpg_project.models.Jugador
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.JugadorService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    var player = Jugador()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Load user
        val user = intent.getSerializableExtra("user") as Usuario
        // Load username in View
        val lblUsername = findViewById<TextView>(R.id.username_textview)
        lblUsername.text = user.username
        // Load user data with character (coins, exp, etc...)
        val id = user.id
        getJugador(id!!)

        val btnLogout = findViewById<ImageButton>(R.id.button_exit)
        btnLogout.setOnClickListener { showAlertDialog() }

        val btnCustomChar = findViewById<ImageButton>(R.id.button_options)
        btnCustomChar.setOnClickListener { openCharacterActivity() }
    }

    override fun onResume() {
        super.onResume()
        // Load user
        val user = intent.getSerializableExtra("user") as Usuario
        // Load username in View
        val lblUsername = findViewById<TextView>(R.id.username_textview)
        lblUsername.text = user.username
        // Load user data with character (coins, exp, etc...)
        val id = user.id
        getJugador(id!!)
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

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.logout) //R.string.dialogTitle
        //set message for alert dialog
        builder.setMessage(R.string.sure_logout)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton(R.string.yes){ _, _ ->
            Toast(this).showCustomToast (
                getString(R.string.error_color),
                "${getString(R.string.logout)}...",
                this)

            val db = DBHelper(this, null)
            db.delete()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        //performing negative action
        builder.setNegativeButton(R.string.no){ _, _ ->
            //Toast(this).showCustomToast ("#DD0000","Incorrect user or password, try again.", this)
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun openCharacterActivity() {
        val intent = Intent(this, CharCreationActivity::class.java)
        intent.putExtra("player", player);
        startActivity(intent)
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

                    val lblExp = findViewById<TextView>(R.id.experience_textview)
                    val lblCoins = findViewById<TextView>(R.id.coins_textview)
                    val lblLevel = findViewById<TextView>(R.id.level_textview)
                    lblExp.text = "Exp: ${player.exp.toString()}"
                    lblCoins.text = "Coins: ${player.monedas.toString()}"
                    lblLevel.text = "Lvl: ${player.nivel.toString()}"
                }
            }
        }
    }
}