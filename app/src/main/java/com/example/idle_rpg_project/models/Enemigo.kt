package com.example.idle_rpg_project.models

import java.io.Serializable
import kotlin.random.Random

data class Enemigo(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = null,
    val hp:Int? = null,
    val atk:Int? = null,
    val def:Int? = null,
    val spd:Int? = null,
    val nivel:Int? = null,
    val exp:Int? = null,
    val monedas:Int? = null
    ) : Serializable {

    fun generateEnemy(level: Int): Enemigo {
        val name = "Enemigo"
        val nivel = level + Random.nextInt(-1, 3)
        val hp = level * Random.nextInt(10, 20)
        val atk = level * Random.nextInt(2, 5)
        val def = level * Random.nextInt(2, 5)
        val spd = level * Random.nextInt(2, 5)
        val exp = level * Random.nextInt(10, 20)
        val monedas = level * Random.nextInt(10, 20)
        val nameFinal = name + level.toString()
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
            var damageReceived = damage - this.def!!
            if(damageReceived < 0){
                damageReceived = 0
            }
            this.hp?.minus(damageReceived)
            return damageReceived
        }

    fun attack(player:Jugador):Int{
        var damage = this.atk!! - player.def!!
        if(damage < 0){
            damage = 0
        }
        player.hp?.minus(damage)
        return damage
    }

    fun giveExp():Int{
        return this.exp!!
    }

}