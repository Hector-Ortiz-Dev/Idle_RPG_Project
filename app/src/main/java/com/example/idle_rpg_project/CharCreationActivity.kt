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
        val adapterType: ArrayAdapter<CharSequence> = ArrayAdapter(this, android.R.layout.simple_spinner_item, types_characters)
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val adapterColor: ArrayAdapter<CharSequence> = ArrayAdapter(this, android.R.layout.simple_spinner_item, color_characters)
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinnerHeadType = findViewById<Spinner>(R.id.spinner_head_type)
        val spinnerLeftArmType = findViewById<Spinner>(R.id.spinner_left_arm_type)
        val spinnerRightArmType = findViewById<Spinner>(R.id.spinner_right_arm_type)
        val spinnerLeftLegType = findViewById<Spinner>(R.id.spinner_left_leg_type)
        val spinnerRightLegType = findViewById<Spinner>(R.id.spinner_right_leg_type)

        val spinnerHeadColor = findViewById<Spinner>(R.id.spinner_head_color)
        val spinnerBodyColor = findViewById<Spinner>(R.id.spinner_body_color)
        val spinnerLeftArmColor = findViewById<Spinner>(R.id.spinner_left_arm_color)
        val spinnerRightArmColor = findViewById<Spinner>(R.id.spinner_right_arm_color)
        val spinnerLeftLegColor = findViewById<Spinner>(R.id.spinner_left_leg_color)
        val spinnerRightLegColor = findViewById<Spinner>(R.id.spinner_right_leg_color)

        spinnerHeadType.onItemSelectedListener = this
        spinnerLeftArmType.onItemSelectedListener = this
        spinnerRightArmType.onItemSelectedListener = this
        spinnerLeftLegType.onItemSelectedListener = this
        spinnerRightLegType.onItemSelectedListener = this

        spinnerHeadColor.onItemSelectedListener = this
        spinnerBodyColor.onItemSelectedListener = this
        spinnerLeftArmColor.onItemSelectedListener = this
        spinnerRightArmColor.onItemSelectedListener = this
        spinnerLeftLegColor.onItemSelectedListener = this
        spinnerRightLegColor.onItemSelectedListener = this

        spinnerHeadType.adapter = adapterType
        spinnerLeftArmType.adapter = adapterType
        spinnerRightArmType.adapter = adapterType
        spinnerLeftLegType.adapter = adapterType
        spinnerRightLegType.adapter = adapterType

        spinnerHeadColor.adapter = adapterColor
        spinnerBodyColor.adapter = adapterColor
        spinnerLeftArmColor.adapter = adapterColor
        spinnerRightArmColor.adapter = adapterColor
        spinnerLeftLegColor.adapter = adapterColor
        spinnerRightLegColor.adapter = adapterColor

        val selectionHeadType = player.cabeza
        val selectionLeftArmType = player.brazoIzq
        val selectionRightArmType = player.brazoDer
        val selectionLeftLegType = player.pieIzq
        val selectionRightLegType = player.pieDer

        val selectionHeadColor = player.cabezaC
        val selectionBodyColor = player.torso
        val selectionLeftArmColor = player.brazoIzqC
        val selectionRightArmColor = player.brazoDerC
        val selectionLeftLegColor = player.pieIzqC
        val selectionRightLegColor = player.pieDerC

        val spinnerPositionHeadType: Int = adapterType.getPosition(selectionHeadType)
        val spinnerPositionLeftArmType: Int = adapterType.getPosition(selectionLeftArmType)
        val spinnerPositionRightArmType: Int = adapterType.getPosition(selectionRightArmType)
        val spinnerPositionLeftLegType: Int = adapterType.getPosition(selectionLeftLegType)
        val spinnerPositionRightLegType: Int = adapterType.getPosition(selectionRightLegType)

        val spinnerPositionHeadColor: Int = adapterColor.getPosition(selectionHeadColor)
        val spinnerPositionBodyColor: Int = adapterColor.getPosition(selectionBodyColor)
        val spinnerPositionLeftArmColor: Int = adapterColor.getPosition(selectionLeftArmColor)
        val spinnerPositionRightArmColor: Int = adapterColor.getPosition(selectionRightArmColor)
        val spinnerPositionLeftLegColor: Int = adapterColor.getPosition(selectionLeftLegColor)
        val spinnerPositionRightLegColor: Int = adapterColor.getPosition(selectionRightLegColor)

        spinnerHeadType.setSelection(spinnerPositionHeadType)
        spinnerLeftArmType.setSelection(spinnerPositionLeftArmType)
        spinnerRightArmType.setSelection(spinnerPositionRightArmType)
        spinnerLeftLegType.setSelection(spinnerPositionLeftLegType)
        spinnerRightLegType.setSelection(spinnerPositionRightLegType)

        spinnerHeadColor.setSelection(spinnerPositionHeadColor)
        spinnerBodyColor.setSelection(spinnerPositionBodyColor)
        spinnerLeftArmColor.setSelection(spinnerPositionLeftArmColor)
        spinnerRightArmColor.setSelection(spinnerPositionRightArmColor)
        spinnerLeftLegColor.setSelection(spinnerPositionLeftLegColor)
        spinnerRightLegColor.setSelection(spinnerPositionRightLegColor)
    }

    private fun drawImage(imageView: ImageView, params: String) {
        val link = "https://movilesmx.000webhostapp.com/idle_rpg/images/jugador/${params}"

        Glide.with(this).load(link).into(imageView)
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//        Toast.makeText(this, "" + types_characters.get(position) + " Selected..", Toast.LENGTH_SHORT).show()
//        Log.e("test", parent.toString())
//        TODO("Not yet implemented")

        when (parent.getId()) {
            R.id.spinner_head_type-> {
                val img = findViewById<ImageView>(R.id.cabeza)
                val spinnerType = findViewById<Spinner>(R.id.spinner_head_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_head_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "head/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_left_arm_type -> {
                val img = findViewById<ImageView>(R.id.brazoIzq)
                val spinnerType = findViewById<Spinner>(R.id.spinner_left_arm_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_left_arm_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "left_arm/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_right_arm_type -> {
                val img = findViewById<ImageView>(R.id.brazoDer)
                val spinnerType = findViewById<Spinner>(R.id.spinner_right_arm_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_right_arm_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "right_arm/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_left_leg_type -> {
                val img = findViewById<ImageView>(R.id.pieIzq)
                val spinnerType = findViewById<Spinner>(R.id.spinner_left_leg_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_left_leg_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "left_leg/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_right_leg_type -> {
                val img = findViewById<ImageView>(R.id.pieDer)
                val spinnerType = findViewById<Spinner>(R.id.spinner_right_leg_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_right_leg_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "right_leg/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_head_color -> {
                val img = findViewById<ImageView>(R.id.cabeza)
                val spinnerType = findViewById<Spinner>(R.id.spinner_head_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_head_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "head/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_body_color -> {
                val img = findViewById<ImageView>(R.id.torso)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_body_color)
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "body/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_left_arm_color -> {
                val img = findViewById<ImageView>(R.id.brazoIzq)
                val spinnerType = findViewById<Spinner>(R.id.spinner_left_arm_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_left_arm_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "left_arm/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_right_arm_color -> {
                val img = findViewById<ImageView>(R.id.brazoDer)
                val spinnerType = findViewById<Spinner>(R.id.spinner_right_arm_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_right_arm_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "right_arm/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_left_leg_color -> {
                val img = findViewById<ImageView>(R.id.pieIzq)
                val spinnerType = findViewById<Spinner>(R.id.spinner_left_leg_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_left_leg_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "left_leg/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
            R.id.spinner_right_leg_color -> {
                val img = findViewById<ImageView>(R.id.pieDer)
                val spinnerType = findViewById<Spinner>(R.id.spinner_right_leg_type)
                val spinnerColor = findViewById<Spinner>(R.id.spinner_right_leg_color)
                val txtType: String = spinnerType.getSelectedItem().toString()
                val txtColor: String = spinnerColor.getSelectedItem().toString()
                val params = "right_leg/${txtType}/${txtColor}.png"

                drawImage(img, params)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}