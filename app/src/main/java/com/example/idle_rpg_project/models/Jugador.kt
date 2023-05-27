package com.example.idle_rpg_project.models

import java.io.Serializable

data class Jugador(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = null,
    val idUsuario:Int? = null,
    val nivel:Int? = null,
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
    val exp:Long? = null,
    val monedas:Int? = null,
    val hp_max:Int? = null,
    val hp:Int? = null,
    val atk:Int? = null,
    val def:Int? = null,
    val spd:Int? = null) : Serializable {

    fun attack(enemy:Enemigo):Int{
        var damage = this.atk!! - enemy.def!!
        if(damage < 0){
            damage = 0
        }
        enemy.hp?.minus(damage)
        return damage
    }

    fun receiveDamage(damage:Int):Int{
        var damageReceived = damage - this.def!!
        if(damageReceived < 0){
            damageReceived = 0
        }
        this.hp?.minus(damageReceived)
        return damageReceived
    }

    private fun levelUp(){
        this.nivel?.plus(1)
        this.hp_max?.plus(10)
        this.hp?.plus(10)
        this.atk?.plus(1)
        this.def?.plus(1)
        this.spd?.plus(1)
    }

    fun receiveExp(exp:Int){
        this.exp?.plus(exp)
        if(this.exp!! >= 100){
            this.levelUp()
        }
    }

    fun receiveCoins(coins:Int){
        this.monedas?.plus(coins)
    }
}