package com.example.idle_rpg_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.adapters.UsuariosGremioAdapter
import com.example.idle_rpg_project.adapters.UsuariosPendientesGremioAdapter
import com.example.idle_rpg_project.models.Gremio
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.GremioService
import showCustomToast

@Suppress("DEPRECATION")
class GuildRequestsActivity : AppCompatActivity() {
    var user: Usuario = Usuario()
    var gremio: Gremio = Gremio()
    private lateinit var usuariosPendientes: ArrayList<Usuario>
    private lateinit var recyclerview: RecyclerView
    private lateinit var guildNameTitle: TextView
    private lateinit var guildLevelTitle: TextView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_requests)

        user = intent.getSerializableExtra("user") as Usuario
        gremio = intent.getSerializableExtra("gremio") as Gremio

        initializeViews()
        getUsuariosPendientes(user.idGremio!!)

        guildNameTitle.text = "${getString(R.string.text_guild)} ${gremio.nombre}"
        guildLevelTitle.text = "${getString(R.string.text_lvl)} ${gremio.nivel}"

        btnBack.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnBack.startAnimation(anim)
            finish()
        }
    }

    private fun initializeViews() {
        // getting the recyclerview by its id
        recyclerview = findViewById(R.id.recyclerview_gremio)

        guildNameTitle = findViewById(R.id.guildNameTitle)
        guildLevelTitle = findViewById(R.id.guildLevelTitle)
        btnBack = findViewById(R.id.btnBack)
    }

    fun getUsuariosPendientes(idGremio: Int) {
        val gremioService = GremioService()

        gremioService.getMiembrosPendientes(idGremio) {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_server_500)}", this)
            }
            else {
                if(it.estatus == 404) {
                    Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_404)}", this)
                }
                else {
                    usuariosPendientes = it.records

                    // this creates a vertical layout Manager
                    recyclerview.layoutManager = LinearLayoutManager(this)

                    // This will pass the ArrayList to our Adapter
                    val adapter = UsuariosPendientesGremioAdapter(this, R.layout.card_users_guild_request, usuariosPendientes)

                    // Setting the Adapter with the recyclerview
                    recyclerview.adapter = adapter
                }
            }
        }
    }
}