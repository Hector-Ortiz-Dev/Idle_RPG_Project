package com.example.idle_rpg_project.models

import android.util.Log
import java.io.Serializable
import kotlin.random.Random

data class Jugador(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = "[Player]",
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
    fun levelUp(){
        this.nivel?.plus(1)
        this.exp = this.exp!! - 100
        this.expSig = this.expSig!! * 2
        this.hp_max = this.hp_max!! + Random.nextInt(0,10)
        this.hp = this.hp_max
        this.atk = this.atk!! + Random.nextInt(0,5)
        this.def = this.def!! + Random.nextInt(0,5)
        this.spd = this.spd!! + Random.nextInt(0,5)

        //Log.e Nuevos datos del jugador
        Log.e("Aviso", "$nombre ha subido de nivel!")
        Log.e("Nivel", this.nivel.toString())
        Log.e("Exp", this.exp.toString())
        Log.e("ExpSig", this.expSig.toString())
        Log.e("HP", this.hp.toString())
        Log.e("HPMax", this.hp_max.toString())
        Log.e("Atk", this.atk.toString())
        Log.e("Def", this.def.toString())
        Log.e("Spd", this.spd.toString())
    }

    //Cambiar codigo duro por expSig
    fun receiveExp(exp:Int): Boolean {
        var levelUp = false
        this.exp = this.exp!! + exp
        if(this.exp!! >= 100){
            levelUp = true
        }
        return levelUp
    }

    fun receiveCoins(coins:Int){
        this.monedas = this.monedas!! + coins
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
}