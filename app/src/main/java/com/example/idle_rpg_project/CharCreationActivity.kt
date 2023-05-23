package com.example.idle_rpg_project

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.idle_rpg_project.models.Jugador
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.JugadorService
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast

@Suppress("DEPRECATION")
class CharCreationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var player: Jugador = Jugador()
    var types_characters = arrayOf("Base", "Bone", "Cream", "Girl", "Octo", "Robot", "Tri", "Yin")
    var color_characters =
        arrayOf("blue", "green", "orange", "pink", "purple", "red", "white", "yellow")

    private lateinit var imgHead: ImageView
    private lateinit var imgTorso: ImageView
    private lateinit var imgBrazoDer: ImageView
    private lateinit var imgBrazoIzq: ImageView
    private lateinit var imgPieDer: ImageView
    private lateinit var imgPieIzq: ImageView
    private lateinit var spinnerHeadType: Spinner
    private lateinit var spinnerLeftArmType: Spinner
    private lateinit var spinnerRightArmType: Spinner
    private lateinit var spinnerLeftLegType: Spinner
    private lateinit var spinnerRightLegType: Spinner
    private lateinit var spinnerHeadColor: Spinner
    private lateinit var spinnerBodyColor: Spinner
    private lateinit var spinnerLeftArmColor: Spinner
    private lateinit var spinnerRightArmColor: Spinner
    private lateinit var spinnerLeftLegColor: Spinner
    private lateinit var spinnerRightLegColor: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_char_creation)

        player = intent.getSerializableExtra("player") as Jugador

        initializeViews()
        loadImages()
        loadInputData()

        val btnConfirm = findViewById<Button>(R.id.confirm_button)
        val btnCancel = findViewById<Button>(R.id.cancel_button)

        btnConfirm.setOnClickListener { saveChangesCustomCharacter() }
        btnCancel.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle(R.string.discard_changes) //R.string.dialogTitle
        //set message for alert dialog
        builder.setMessage(R.string.sure_discard_changes)
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

    private fun initializeViews() {
        // IMAGES
        imgHead = findViewById<ImageView>(R.id.cabeza)
        imgTorso = findViewById<ImageView>(R.id.torso)
        imgBrazoDer = findViewById<ImageView>(R.id.brazoDer)
        imgBrazoIzq = findViewById<ImageView>(R.id.brazoIzq)
        imgPieDer = findViewById<ImageView>(R.id.pieDer)
        imgPieIzq = findViewById<ImageView>(R.id.pieIzq)

        // INPUTS
        spinnerHeadType = findViewById<Spinner>(R.id.spinner_head_type)
        spinnerLeftArmType = findViewById<Spinner>(R.id.spinner_left_arm_type)
        spinnerRightArmType = findViewById<Spinner>(R.id.spinner_right_arm_type)
        spinnerLeftLegType = findViewById<Spinner>(R.id.spinner_left_leg_type)
        spinnerRightLegType = findViewById<Spinner>(R.id.spinner_right_leg_type)

        spinnerHeadColor = findViewById<Spinner>(R.id.spinner_head_color)
        spinnerBodyColor = findViewById<Spinner>(R.id.spinner_body_color)
        spinnerLeftArmColor = findViewById<Spinner>(R.id.spinner_left_arm_color)
        spinnerRightArmColor = findViewById<Spinner>(R.id.spinner_right_arm_color)
        spinnerLeftLegColor = findViewById<Spinner>(R.id.spinner_left_leg_color)
        spinnerRightLegColor = findViewById<Spinner>(R.id.spinner_right_leg_color)
    }

    private fun loadImages() {
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

        val spinnerPositionHeadType: Int = adapterType.getPosition(player.cabeza)
        val spinnerPositionLeftArmType: Int = adapterType.getPosition(player.brazoIzq)
        val spinnerPositionRightArmType: Int = adapterType.getPosition(player.brazoDer)
        val spinnerPositionLeftLegType: Int = adapterType.getPosition(player.pieIzq)
        val spinnerPositionRightLegType: Int = adapterType.getPosition(player.pieDer)

        val spinnerPositionHeadColor: Int = adapterColor.getPosition(player.cabezaC)
        val spinnerPositionBodyColor: Int = adapterColor.getPosition(player.torso)
        val spinnerPositionLeftArmColor: Int = adapterColor.getPosition(player.brazoIzqC)
        val spinnerPositionRightArmColor: Int = adapterColor.getPosition(player.brazoDerC)
        val spinnerPositionLeftLegColor: Int = adapterColor.getPosition(player.pieIzqC)
        val spinnerPositionRightLegColor: Int = adapterColor.getPosition(player.pieDerC)

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

        when (parent.getId()) {
            R.id.spinner_head_type -> {
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

    private fun saveChangesCustomCharacter() {
        val data = Jugador(
            method = "setCustomCharacter",
            id = player.idUsuario,
            cabeza = spinnerHeadType.getSelectedItem().toString(),
            cabezaC = spinnerHeadColor.getSelectedItem().toString(),
            torso = spinnerBodyColor.getSelectedItem().toString(),
            brazoIzq = spinnerLeftArmType.getSelectedItem().toString(),
            brazoIzqC = spinnerLeftArmColor.getSelectedItem().toString(),
            brazoDer = spinnerRightArmType.getSelectedItem().toString(),
            brazoDerC = spinnerRightArmColor.getSelectedItem().toString(),
            pieIzq = spinnerLeftLegType.getSelectedItem().toString(),
            pieIzqC = spinnerLeftLegColor.getSelectedItem().toString(),
            pieDer = spinnerRightLegType.getSelectedItem().toString(),
            pieDerC = spinnerRightLegColor.getSelectedItem().toString()
        )

        val jugadorService = JugadorService()
        jugadorService.post(data) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()
                Toast(this).showCustomToast (
                    getString(R.string.error_color),
                    "${getString(R.string.error_server_500)}",
                    this)

            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()
                    Toast(this).showCustomToast (
                        getString(R.string.error_color),
                        "${getString(R.string.error_save_404)}",
                        this)
                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()
                    Toast(this).showCustomToast (
                        getString(R.string.success_color),
                        "${getString(R.string.success_save_char)}",
                        this)
                    finish()
                }
            }
        }
    }
}