package com.example.idle_rpg_project.models

import java.io.Serializable
import kotlin.random.Random

data class Enemigo(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = null,
    var hp:Int? = null,
    var hpMax:Int? = null,
    val atk:Int? = null,
    val def:Int? = null,
    val spd:Int? = null,
    val nivel:Int? = null,
    val exp:Int? = null,
    val monedas:Int? = null,
    var posicion:Int? = 0,
    val indexImg:Int = 0,
    ) : Serializable {

    fun generateEnemy(level: Int): Enemigo {
        var type_enemies = arrayOf(
            "Rizk", "Darck Rizk",
            "Gubby", "Dark Gubby",
            "Slug", "Dark Slug",
            "Worp", "Dark Worp",
            "Dag", "Dark Dag",
            "Toñafro", "Dark Toñafro")

        val name = "Enemigo"
        var nivel = level + Random.nextInt(-1, 3)
        if(nivel < 1){
            nivel = 1
        }

        var index = 0
        if(level >= 40) {
            index = Random.nextInt(1, 12)
        }
        else {
            index = Random.nextInt(1, 10)
        }

        val hp = nivel * Random.nextInt(10, 20)
        val atk = nivel * Random.nextInt(3, 7)
        val def = nivel * Random.nextInt(2, 5)
        val spd = nivel * Random.nextInt(4, 9)
        val exp = nivel * Random.nextInt(10, 20)
        val monedas = nivel * Random.nextInt(10, 20)
        val nameFinal = "${type_enemies[index - 1]} Lv.$nivel"
        return Enemigo(
            nombre = nameFinal,
            nivel = nivel,
            hp = hp,
            hpMax = hp,
            atk = atk,
            def = def,
            spd = spd,
            exp = exp,
            monedas = monedas,
            indexImg = index
        )
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