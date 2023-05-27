package com.example.idle_rpg_project.models

import java.io.Serializable
import kotlin.random.Random

data class Enemigo(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = null,
    var hp:Int? = null,
    val atk:Int? = null,
    val def:Int? = null,
    val spd:Int? = null,
    val nivel:Int? = null,
    val exp:Int? = null,
    val monedas:Int? = null,
    var posicion:Int? = 0
    ) : Serializable {

    fun generateEnemy(level: Int): Enemigo {
        val name = "Enemigo"
        var nivel = level + Random.nextInt(-1, 3)
        if(nivel < 1){
            nivel = 1
        }
        val hp = level * Random.nextInt(10, 20)
        val atk = level * Random.nextInt(3, 7)
        val def = level * Random.nextInt(2, 5)
        val spd = level * Random.nextInt(4, 9)
        val exp = level * Random.nextInt(10, 20)
        val monedas = level * Random.nextInt(10, 20)
        val nameFinal = "$name Lv.$nivel"
        return Enemigo(
            nombre = nameFinal,
            nivel = nivel,
            hp = hp,
            atk = atk,
            def = def,
            spd = spd,
            exp = exp,
            monedas = monedas)
    }

    fun receiveDamage(damage:Int):Int{
        this.hp = this.hp!!- damage
        if (this.hp!! < 0){
            this.hp = 0
        }
        return damage
    }

    fun attack(player:Jugador):Int{
        var damage = this.atk!! - player.def!!
        if(damage < 0){
            damage = 0
        }
        return damage
    }

    fun giveExp():Int{
        return this.exp!!
    }

}