package com.example.idle_rpg_project

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.idle_rpg_project.adapters.HistoryBattleAdapter
import com.example.idle_rpg_project.models.Jugador
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.JugadorService
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import com.example.idle_rpg_project.utils.SystemBattle
import showCustomToast
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var isRunning: Boolean = false

    lateinit var context: Activity
    var user = Usuario()
    var player = Jugador()
    private var systemBattle: SystemBattle? = null
    private var history: ArrayList<String> = ArrayList()

    private lateinit var recyclerview: RecyclerView

    private lateinit var enemyImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Load user
        user = intent.getSerializableExtra("user") as Usuario
        // Load username in View
        val lblUsername = findViewById<TextView>(R.id.username_textview)
        lblUsername.text = user.username
        // Load user data with character (coins, exp, etc...)
        val id = user.id
        getJugador(id!!)

        initializeViews()

        recyclerview = findViewById(R.id.recycler_view_history)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // This will pass the ArrayList to our Adapter
        val adapter = HistoryBattleAdapter(R.layout.card_history_battle, history)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        val btnGuild = findViewById<ImageButton>(R.id.button_guild)
        val btnShop = findViewById<ImageButton>(R.id.button_shop)
        val btnSword = findViewById<ImageButton>(R.id.button_sword)
        val btnOptions = findViewById<ImageButton>(R.id.button_options)
        val btnLogout = findViewById<ImageButton>(R.id.button_exit)

        btnGuild.setOnClickListener {
            openGuildActivity()
            isRunning = false
        }
        btnShop.setOnClickListener {
            openShopActivity()
            isRunning = false
        }
        btnSword.setOnClickListener {
            openCharacterActivity()
            isRunning = false
        }
        btnOptions.setOnClickListener {
            openEditProfileActivity()
            isRunning = false
        }
        btnLogout.setOnClickListener {
            showAlertDialog()
            isRunning = false
        }
    }

    private fun initializeViews() {
        context = this

        enemyImg = findViewById(R.id.enemy)
    }

    private fun startCyclicExecution() {
        isRunning = true

        val thread = Thread {
            while (isRunning) {
                // Tu función cíclica aquí
                ejecutarFuncion()

                // Agregar una pausa si es necesario
                Thread.sleep(1000) // Ejemplo: pausa de 1 segundo
            }
        }

        thread.start()
    }

    private fun ejecutarFuncion() {

        //Lógica de tu función cíclica aquí
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_icon)
        val icon = findViewById<ImageView>(R.id.icon)
        icon.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // Método llamado cuando la animación comienza
                icon.isGone = false
            }

            override fun onAnimationEnd(animation: Animation) {
                // Método llamado cuando la animación termina
                // Aquí puedes ejecutar el código que deseas después de la animación
                icon.isGone = true
            }

            override fun onAnimationRepeat(animation: Animation) {
                // Método llamado cuando la animación se repite
            }
        })

        val animation2 = AnimationUtils.loadAnimation(this, R.anim.bounce)
        val character = findViewById<ConstraintLayout>(R.id.character)
        character.startAnimation(animation2)

        val animation3 = AnimationUtils.loadAnimation(this, R.anim.bounce_move_enemy)
        val enemy = findViewById<ImageView>(R.id.enemy)
        enemy.startAnimation(animation3)

        //Comienza la batalla
        if (systemBattle == null)
            systemBattle = SystemBattle(player)

        var result = systemBattle!!.detalleBattle
        var estado = systemBattle!!.estadoTxt
//        val result = systemBattle!!.battle(player)
        systemBattle!!.cycleBattle(player)
        systemBattle!!.startActionBattle(player)

        Log.e("Result", result)

        if (result.isNullOrEmpty() && estado == "Espera"){
            return
        }
        else if (!result.isNullOrEmpty()){
//        // Es necesario para que muestre ordenado el desmadre en el recycler view
            history.reverse()
            history.add(result)
            history.reverse()
        }


//        // This will pass the ArrayList to our Adapter
//        val adapter = HistoryBattleAdapter(R.layout.card_history_battle, history)
//        // Setting the Adapter with the recyclerview
//        recyclerview.adapter = adapter
        Handler(Looper.getMainLooper()).post {
            recyclerview.getAdapter()?.notifyDataSetChanged()

            val actualEnemy = systemBattle!!.getEnemy()
            val enemyUrl = "https://movilesmx.000webhostapp.com/idle_rpg/images/enemies/${actualEnemy.indexImg}.png"
            Glide.with(context).load(enemyUrl).into(enemyImg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Detener el ciclo al destruir la actividad
        stopCyclicExecution()
    }

    private fun stopCyclicExecution() {
        isRunning = false

//        // Iniciar el ciclo
//        startCyclicExecution()
    }

    override fun onResume() {
        super.onResume()
//        // Load user
//        val user = intent.getSerializableExtra("user") as Usuario
//        // Load username in View
//        val lblUsername = findViewById<TextView>(R.id.username_textview)
//        lblUsername.text = user.username
//        // Load user data with character (coins, exp, etc...)
        val id = user.id
        getUserById(id!!)
        getJugador(id!!)

        Handler().postDelayed({
            isRunning = true
            // Iniciar el ciclo
            startCyclicExecution()
        }, 1000)
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
//        //performing negative action
//        builder.setNegativeButton(R.string.no){ _, _ ->
//            //Toast(this).showCustomToast ("#DD0000","Incorrect user or password, try again.", this)
//        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun openCharacterActivity() {
        val intent = Intent(this, CharacterActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("player", player)
        startActivity(intent)
    }

    private fun getUserById(id: Int) {

        val usuarioService = UsuarioService()

        usuarioService.getById(id) {
            if (it == null) {
                Toast(this).showCustomToast ("#DD0000",getString(R.string.error_server_500), this)
            }
            else {
                if(it.estatus == 404){
                    Toast(this).showCustomToast ("#DD0000", getString(R.string.error_save_404), this)
                }
                else {
                    user = it.records[0]

                    // Load username in View
                    val lblUsername = findViewById<TextView>(R.id.username_textview)
                    lblUsername.text = user.username
                }
            }
        }
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

    private fun openGuildActivity() {
        if(user.idGremio == null) {
            val intent = Intent(this, SearchGuildActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)

//            Toast(this).showCustomToast (
//                getString(R.string.success_color),
//                "Se abre lista de gremios",
//                this)
        }
        else {
            if(user.espera == 1) {
                Toast(this).showCustomToast (
                    getString(R.string.success_color),
                    "Estas en espera de un gremio",
                    this)
            }
            else if(user.lider == 1) {
                val intent = Intent(this, GuildActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
            else if(user.lider == 0){
                val intent = Intent(this, GuildActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }
        }
    }

    private fun openEditProfileActivity() {
        val intent = Intent(this, EditProfileActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }

    private fun openShopActivity() {
        val intent = Intent(this, ShopActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
    }
}