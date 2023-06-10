package com.example.idle_rpg_project.models

import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.idle_rpg_project.MainActivity
import com.example.idle_rpg_project.R
import com.example.idle_rpg_project.services.JugadorService
import com.example.idle_rpg_project.services.UsuarioService
import com.example.idle_rpg_project.utils.DBHelper
import showCustomToast
import java.io.Serializable
import kotlin.random.Random

data class Jugador(
    val method:String? = null,
    val id:Int? = null,
    var nombre:String? = "[Player]",
    val idUsuario:Int? = null,
    var nivel:Int? = null,
    val cabeza:String? = null,
    val cabezaC:String? = null,
    val torso:String? = null,
    val brazoIzq:String? = null,
    val brazoIzqC:String? = null,
    val brazoDer:String? = null,
    val brazoDerC:String? = null,
    val pieIzq:String? = null,
    val pieIzqC:String? = null,
    val pieDer:String? = null,
    val pieDerC:String? = null,
    var exp:Long? = null,
    var expSig:Long? = null,
    var monedas:Int? = null,
    var posicion:Int? = 0,
    var hp_max:Int? = null,
    var hp:Int? = null,
    var atk:Int? = null,
    var def:Int? = null,
    var spd:Int? = null) : Serializable {

//    init {
//        if(this.nivel != null){
//            getNextLevel()
//        }
//    }

    fun attack(enemy:Enemigo):Int{
        var damage = this.atk!! - enemy.def!!
        if(damage < 0){
            damage = 0
        }
        return damage
    }

    fun receiveDamage(damage:Int):Int{
        this.hp = this.hp!! - damage
        if (this.hp!! < 0){
            this.hp = 0
        }
        return damage
    }

    //Cambiar por funcion base de datos
//    fun levelUp(){
//        this.nivel?.plus(1)
//        this.exp = this.exp!! - 100
//        this.expSig = this.expSig!! * 2
//        this.hp_max = this.hp_max!! + Random.nextInt(0,10)
//        this.hp = this.hp_max
//        this.atk = this.atk!! + Random.nextInt(0,5)
//        this.def = this.def!! + Random.nextInt(0,5)
//        this.spd = this.spd!! + Random.nextInt(0,5)
//
//        //Log.e Nuevos datos del jugador
//        Log.e("Aviso", "$nombre ha subido de nivel!")
//        Log.e("Nivel", this.nivel.toString())
//        Log.e("Exp", this.exp.toString())
//        Log.e("ExpSig", this.expSig.toString())
//        Log.e("HP", this.hp.toString())
//        Log.e("HPMax", this.hp_max.toString())
//        Log.e("Atk", this.atk.toString())
//        Log.e("Def", this.def.toString())
//        Log.e("Spd", this.spd.toString())
//    }

    //Cambiar codigo duro por expSig
    fun receiveExp(exp:Int): Boolean {
        var levelUp = false
        this.exp = this.exp!! + exp
        // Funcion para agregar Exp
        //addExp()

        if(this.exp!! >= this.expSig!!){
            levelUp = true
            // Funcion para subir de nivel
            //levelUp()
        }
        return levelUp
    }

    fun receiveCoins(coins:Int){
        this.monedas = this.monedas!! + coins

        // Metodo para agregar monedas
        //this.addMonedas(this.monedas)
    }

    fun loseCoins(coins:Int): Int {
        this.monedas = this.monedas!! - coins
        if (this.monedas!! < 0){
            this.monedas = 0
        }
        return coins
    }

    fun revive(){
        this.hp = this.hp_max
    }

    fun getNextLevel() {
        val nextLevel = this.nivel!! + 1
        val data = Jugador(method = "nextLevel", nivel = nextLevel)

        val jugadorService = JugadorService()

        jugadorService.post(data) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()

            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()

                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()

                    val data: Jugador = it.records[0]

                    this.expSig = data.exp
                }
            }
        }
    }

    fun addExp() {
        val data = Jugador(method = "addExp", idUsuario = this.idUsuario, exp = this.exp)

        val jugadorService = JugadorService()

        jugadorService.post(data) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()

            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()

                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()

                    val data: Jugador = it.records[0]

                    this.exp = data.exp
                }
            }
        }
    }

    fun levelUp() {
        val data = Jugador(method = "levelUp", idUsuario = this.idUsuario, exp = this.exp)

        val jugadorService = JugadorService()

        jugadorService.post(data) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()

            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()

                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()

                    val data: Jugador = it.records[0]
                    this.nivel = data.nivel
                    this.exp = data.exp
                    this.monedas = data.monedas
                    this.hp_max = data.hp_max
                    this.hp = data.hp
                    this.atk = data.atk
                    this.def = data.def
                    this.spd = data.spd
                }
            }
        }
    }

    fun addMonedas() {
        val data = Jugador(method = "addMonedas", idUsuario = this.idUsuario, monedas = this.monedas)

        val jugadorService = JugadorService()

        jugadorService.post(data) {
            if (it == null) {
                //Toast.makeText(applicationContext, "Error to call server request.", Toast.LENGTH_SHORT).show()

            }
            else {
                if(it.estatus == 404){
                    //Toast.makeText(applicationContext, "Incorrect user or password, try again.", Toast.LENGTH_SHORT).show()

                }
                else {
                    //Toast.makeText(applicationContext, "Loading information...", Toast.LENGTH_SHORT).show()

                    val data: Jugador = it.records[0]
                }
            }
        }
    }
}