package com.example.idle_rpg_project

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.idle_rpg_project.models.Jugador

@Suppress("DEPRECATION")
class CharCreationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var player: Jugador = Jugador()
    var types_characters = arrayOf("Base", "Bone", "Cream", "Girl", "Octo", "Robot", "Tri", "Yin")
    var color_characters = arrayOf("blue", "green", "orange", "pink", "purple", "red", "white", "yellow")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_creation)

        player = intent.getSerializableExtra("player") as Jugador

        loadImages()

        loadInputData()

    }

    private fun loadImages() {
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

    private fun loadInputData() {
        val spinnerHeadType = findViewById<Spinner>(R.id.spinner_head_type)
        val spinnerHeadColor = findViewById<Spinner>(R.id.spinner_head_color)
        val spinnerBodyColor = findViewById<Spinner>(R.id.spinner_body_color)

        spinnerHeadType.onItemSelectedListener = this
        spinnerHeadColor.onItemSelectedListener = this
        spinnerBodyColor.onItemSelectedListener = this

        val adapterType: ArrayAdapter<CharSequence> = ArrayAdapter(this, android.R.layout.simple_spinner_item, types_characters)
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapterColor: ArrayAdapter<CharSequence> = ArrayAdapter(this, android.R.layout.simple_spinner_item, color_characters)
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerHeadType.adapter = adapterType
        spinnerHeadColor.adapter = adapterColor
        spinnerBodyColor.adapter = adapterColor

        val selectionHeadType = player.cabeza
        val selectionHeadColor = player.cabezaC
        val selectionBodyColor = player.torso
        val spinnerPositionHeadType: Int = adapterType.getPosition(selectionHeadType)
        val spinnerPositionHeadColor: Int = adapterColor.getPosition(selectionHeadColor)
        val spinnerPositionBodyColor: Int = adapterColor.getPosition(selectionBodyColor)

        spinnerHeadType.setSelection(spinnerPositionHeadType)
        spinnerHeadColor.setSelection(spinnerPositionHeadColor)
        spinnerBodyColor.setSelection(spinnerPositionBodyColor)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "" + types_characters.get(position) + " Selected..", Toast.LENGTH_SHORT).show()
        Log.e("test", parent.toString())
//        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}