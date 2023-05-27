package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.idle_rpg_project.models.Jugador
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.JugadorService
import showCustomToast

@Suppress("DEPRECATION")
class CharacterActivity : AppCompatActivity() {
    var user: Usuario = Usuario()
    var player: Jugador = Jugador()

    private lateinit var txtUser: TextView
    private lateinit var txtLevel: TextView

    private lateinit var txtExp: TextView
    private lateinit var txtCoins: TextView
    private lateinit var txtHp: TextView
    private lateinit var txtAtk: TextView
    private lateinit var txtDef: TextView
    private lateinit var txtSpd: TextView

    private lateinit var imgHead: ImageView
    private lateinit var imgTorso: ImageView
    private lateinit var imgBrazoDer: ImageView
    private lateinit var imgBrazoIzq: ImageView
    private lateinit var imgPieDer: ImageView
    private lateinit var imgPieIzq: ImageView
    private lateinit var character: ConstraintLayout

    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        user = intent.getSerializableExtra("user") as Usuario
        player = intent.getSerializableExtra("player") as Jugador

        initializeViews()
        loadData()
        getJugador(user.id!!)

        val btnCustomCharacter = findViewById<Button>(R.id.btnCustomCharacter)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnCustomCharacter.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnCustomCharacter.startAnimation(anim)
            openCustomCharacterActivity()
        }
        btnBack.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnBack.startAnimation(anim)
            finish()
        }
    }
    override fun onResume() {
        super.onResume()

        getJugador(user.id!!)

        startCyclicExecution()
    }

    private fun startCyclicExecution() {
        isRunning = true

        val thread = Thread {
            while (isRunning) {
                // Tu función cíclica aquí
                mueveTorso()

                // Agregar una pausa si es necesario
                Thread.sleep(2000) // Ejemplo: pausa de 1 segundo
            }
        }
        val thread2 = Thread {
            while (isRunning) {
                // Tu función cíclica aquí
                mueveCabeza()

                // Agregar una pausa si es necesario
                Thread.sleep(1300) // Ejemplo: pausa de 1 segundo
            }
        }

        thread.start()
        thread2.start()
    }

    private fun mueveTorso() {
        // Lógica de tu función cíclica aquí
        val animation = AnimationUtils.loadAnimation(this, R.anim.zoom)
        imgBrazoDer.startAnimation(animation)
        imgBrazoIzq.startAnimation(animation)
        imgPieDer.startAnimation(animation)
        imgPieIzq.startAnimation(animation)
    }

    private fun mueveCabeza() {
        // Lógica de tu función cíclica aquí
        val animation = AnimationUtils.loadAnimation(this, R.anim.zoom)
        imgTorso.startAnimation(animation)
        imgHead.startAnimation(animation)
    }

    override fun onDestroy() {
        super.onDestroy()

        // Detener el ciclo al destruir la actividad
        stopCyclicExecution()
    }

    private fun stopCyclicExecution() {
        isRunning = false
    }

    private fun initializeViews() {
        txtUser = findViewById(R.id.txtUsername)
        txtLevel = findViewById(R.id.txtLevel)

        txtExp = findViewById(R.id.txtExp)
        txtCoins = findViewById(R.id.txtCoins)
        txtHp = findViewById(R.id.txtHp)
        txtAtk = findViewById(R.id.txtAtk)
        txtDef = findViewById(R.id.txtDef)
        txtSpd = findViewById(R.id.txtSpeed)

        // IMAGES
        imgHead = findViewById(R.id.cabeza)
        imgTorso = findViewById(R.id.torso)
        imgBrazoDer = findViewById(R.id.brazoDer)
        imgBrazoIzq = findViewById(R.id.brazoIzq)
        imgPieDer = findViewById(R.id.pieDer)
        imgPieIzq = findViewById(R.id.pieIzq)
        character = findViewById(R.id.character)
    }

    private fun loadData() {
        txtUser.text = user.username
        txtLevel.text = "${getString(R.string.text_lvl)} ${player.nivel}"

        txtExp.text = player.exp.toString()
        txtCoins.text = player.monedas.toString()
        txtHp.text = "${player.hp.toString()} / ${player.hp_max.toString()}"
        txtAtk.text = player.atk.toString()
        txtDef.text = player.def.toString()
        txtSpd.text = player.spd.toString()
    }

    private fun openCustomCharacterActivity() {
        val intent = Intent(this, CharCreationActivity::class.java)
        intent.putExtra("player", player);
        startActivity(intent)
    }

    private fun getJugador(id: Int) {
        val jugadorService = JugadorService()
        jugadorService.getById(id) {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_server_500)}", this)
            }
            else {
                if(it.estatus == 404){
                    Toast(this).showCustomToast (getString(R.string.error_color), getString(R.string.error_save_404), this)
                }
                else{
                    player = it.records[0]

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