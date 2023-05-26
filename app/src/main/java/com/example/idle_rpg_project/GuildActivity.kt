package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.adapters.UsuariosGremioAdapter
import com.example.idle_rpg_project.models.Gremio
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.GremioService
import com.example.idle_rpg_project.services.UsuarioService
import showCustomToast

@Suppress("DEPRECATION")
class GuildActivity : AppCompatActivity() {
    var user: Usuario = Usuario()
    var gremio: Gremio = Gremio()
    var liderGremio: Usuario = Usuario()
    private lateinit var usuariosGremio: ArrayList<Usuario>

    private lateinit var recyclerview: RecyclerView
    private lateinit var btnBack: Button
    private lateinit var btnPendientes: Button
    private lateinit var guildNameTitle: TextView
    private lateinit var guildLevelTitle: TextView
    private lateinit var txtGuildExp: TextView
    private lateinit var txtMembers: TextView
    private lateinit var txtGuildMaster: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild)

        user = intent.getSerializableExtra("user") as Usuario

        initializeViews()

        getUsuariosGremio()

        if(user.lider == 0){
            btnPendientes.isGone = true
        }

        btnBack.setOnClickListener { finish() }
        btnPendientes.setOnClickListener { openGuildRequestActivity() }
    }

    private fun initializeViews() {
        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recyclerview_gremio)
        btnBack = findViewById(R.id.btnBack)
        btnPendientes = findViewById(R.id.btnPendientes)

        guildNameTitle = findViewById(R.id.guildNameTitle)
        guildLevelTitle = findViewById(R.id.guildLevelTitle)
        txtGuildExp = findViewById(R.id.txtGuildExp)
        txtMembers = findViewById(R.id.txtMembers)
        txtGuildMaster = findViewById(R.id.txtGuildMaster)
    }

    fun getUsuariosGremio() {
        val usuarioService = UsuarioService()

        usuarioService.getAll {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_server_500)}", this)
            }
            else {
                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(this)

                usuariosGremio = it.records.filter { x -> x.idGremio == user.idGremio && (x.lider == 1 || x.espera == 0) } as ArrayList<Usuario>
                liderGremio = it.records.find { x -> x.idGremio == user.idGremio && x.lider == 1 } as Usuario

                // This will pass the ArrayList to our Adapter
                val adapter = UsuariosGremioAdapter(this, R.layout.card_users_guild, usuariosGremio)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

                getGremio(user.idGremio!!)
            }
        }
    }

    fun getGremio(idGremio: Int) {
        val gremioService = GremioService()

        gremioService.getById(idGremio) {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_server_500)}", this)
            }
            else {
                if(it.estatus == 404) {
                    Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_404)}", this)
                }
                else {
                    gremio = it.records[0]

                    guildNameTitle.text = "${getString(R.string.text_guild)} ${gremio.nombre}"
                    guildLevelTitle.text = "${getString(R.string.text_lvl)} ${gremio.nivel}"
                    txtGuildExp.text = "${getString(R.string.text_exp)} ${gremio.exp}"
                    txtMembers.text = "${getString(R.string.text_members)} ${usuariosGremio.size} / ${gremio.cantidad}"
                    txtGuildMaster.text = "${getString(R.string.text_guild_master)} ${liderGremio.username}"
                }
            }
        }
    }

    fun openGuildRequestActivity() {
        val intent = Intent(this, GuildRequestsActivity::class.java)
        intent.putExtra("user", user)
        intent.putExtra("gremio", gremio)
        startActivity(intent)
    }
}