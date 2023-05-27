package com.example.idle_rpg_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Html.ImageGetter
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.adapters.GremioAdapter
import com.example.idle_rpg_project.models.Gremio
import com.example.idle_rpg_project.models.Jugador
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.GremioService
import showCustomToast

@Suppress("DEPRECATION")
class SearchGuildActivity : AppCompatActivity() {
    var user: Usuario = Usuario()
    private lateinit var gremios: ArrayList<Gremio>
    private lateinit var gremiosFilter: ArrayList<Gremio>


    private lateinit var btnCreate: Button
    private lateinit var btnBack: Button
    private lateinit var txtSearch: EditText
    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_guild)

        val context = this

        user = intent.getSerializableExtra("user") as Usuario

        initializeViews()

        getGremios()

        btnCreate.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnCreate.startAnimation(anim)
            openCreateGuildActivity()
        }
        btnBack.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            btnBack.startAnimation(anim)
            finish()
        }

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(s.isNullOrEmpty()){
                    recyclerview.layoutManager = LinearLayoutManager(context)
                    val adapter = GremioAdapter(context, R.layout.card_gremio, gremios)
                    recyclerview.adapter = adapter
                    return
                }

                gremiosFilter = gremios.filter { it.nombre?.toLowerCase()!!.contains(s) } as ArrayList<Gremio>
                recyclerview.layoutManager = LinearLayoutManager(context)
                val adapter = GremioAdapter(context, R.layout.card_gremio, gremiosFilter)
                recyclerview.adapter = adapter
            }
        })
    }

    private fun initializeViews() {
        btnCreate = findViewById(R.id.btnCreateGuild)
        btnBack = findViewById(R.id.btnBack)
        txtSearch = findViewById(R.id.searchGuildName)

        // getting the recyclerview by its id
        recyclerview = findViewById<RecyclerView>(R.id.recyclerview_gremio)
    }

    private fun openCreateGuildActivity() {
        val intent = Intent(this, CreateGuildActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
        finish()
    }

    fun getGremios() {
        val gremioService = GremioService()

        gremioService.getAll {
            if (it == null) {
                Toast(this).showCustomToast (getString(R.string.error_color),"${getString(R.string.error_server_500)}", this)
            }
            else {
                // this creates a vertical layout Manager
                recyclerview.layoutManager = LinearLayoutManager(this)

                gremios = it.records
                gremiosFilter = it.records

                // This will pass the ArrayList to our Adapter
                val adapter = GremioAdapter(this, R.layout.card_gremio, it.records)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
            }
        }
    }
}