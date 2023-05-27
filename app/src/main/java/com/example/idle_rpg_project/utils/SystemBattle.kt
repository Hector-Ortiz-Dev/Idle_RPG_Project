package com.example.idle_rpg_project.utils

import android.util.Log
import com.example.idle_rpg_project.models.Enemigo
import com.example.idle_rpg_project.models.Jugador

class SystemBattle {

    //Sistema de batalla idle rpg
    fun battle(player: Jugador, enemy: Enemigo):String{
        var result = ""
        var damage = 0
        var damageReceived = 0
        var turn = 0

        //Determinar valor maximo de la timeline en base de la velocidad de los

        println("Comienza la batalla entre ${player.nombre} y ${enemy.nombre}" ) //Imprime que comienza la batalla
        //Espera 1 segundo

        //while(player.hp!! > 0 && enemy.hp!! > 0){ //Mientras el jugador y el enemigo tengan vida
            turn++ //Aumenta el turno
            damage = player.attack(enemy) //Ataca el jugador
            damageReceived = enemy.receiveDamage(damage) //Recibe daño el enemigo

            result += "Turno $turn: ${player.nombre} ataca a ${enemy.nombre} y le hace $damage de daño. ${enemy.nombre} tiene ${enemy.hp} de vida.\n" //Imprime el resultado del turno

            if(enemy.hp!! <= 0){ //Si el enemigo muere
                result += "${enemy.nombre} ha muerto.\n" //Imprime que el enemigo ha muerto
                //break //Termina el bucle
            }

            damage = enemy.attack(player) //Ataca el enemigo
            damageReceived = player.receiveDamage(damage) //Recibe daño el jugador
            result += "Turno $turn: ${enemy.nombre} ataca a ${player.nombre} y le hace $damage de daño. ${player.nombre} tiene ${player.hp} de vida.\n" //Imprime el resultado del turno
            if(player.hp!! <= 0){ //Si el jugador muere
                result += "${player.nombre} ha muerto.\n" //Imprime que el jugador ha muerto
                //break //Termina el bucle
            }
        //}

        return result
    }

    private fun getEnemy(enemy: Enemigo) {

    }

}