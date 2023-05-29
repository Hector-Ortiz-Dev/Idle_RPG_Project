package com.example.idle_rpg_project.utils

import android.util.Log
import com.example.idle_rpg_project.models.Enemigo
import com.example.idle_rpg_project.models.Jugador

class SystemBattle(player: Jugador) {
    //init {
    private var enemy: Enemigo = Enemigo().generateEnemy(player.nivel!!) //Genera un nuevo enemigo
    //}

    //Sistema de batalla idle rpg
    fun battle(player: Jugador):String{

        if (enemy == null){ //Si no hay enemigo
            enemy = Enemigo().generateEnemy(player.nivel!!) //Genera un nuevo enemigo
        } else if (enemy!!.hp!! <= 0){ //Si el enemigo muere
            enemy = Enemigo().generateEnemy(player.nivel!!) //Genera un nuevo enemigo
        }

        //Log.e datos del jugador
//        Log.e("Player", player.toString())

        //Log.e datos del enemigo
//        Log.e("Enemy", enemy.toString())

        //Variables
        var result = ""
        var damage = 0

        //Determinar valor maximo de la timeline en base de la velocidad de las entidades
        var timeline:Int = 0

        timeline = if (player.spd!! > enemy.spd!!){ //Si la velocidad del jugador es mayor que la del enemigo
            player.spd!! * 10
        }else{
            enemy.spd!! * 10
        }
//        Log.e("Timeline", "Timeline meta: $timeline")

        //Sumar velocidad a la posicion
        player.posicion = player.posicion?.plus(player.spd!!) //Suma la velocidad del jugador a su posicion
//        Log.e("PlayerPos", "Player position: " + player.posicion.toString())
        enemy.posicion = enemy.posicion?.plus(enemy.spd!!) //Suma la velocidad del enemigo a su posicion
//        Log.e("EnemyPos", "Enemy position: " + enemy.posicion.toString())

        //Comienza la batalla
//        result += "Comienza la batalla.\n"

        // Turno del jugador
        if(player.posicion!! >= timeline) {
            player.posicion = 0 //Resetea la posicion del jugador
            result += "${player.nombre} realizara una accion\n" //Imprime que comienza la batalla

            damage = player.attack(enemy) //Ataca el jugador
            enemy.receiveDamage(damage) //Recibe daño el enemigo
            result += "${player.nombre} ataca a ${enemy.nombre} y le hace $damage de daño.\n${enemy.nombre} tiene ${enemy.hp} de vida.\n" //Imprime el resultado del turno

            if (enemy.hp!! <= 0) { //Si el enemigo muere
                result += "${enemy.nombre} ha muerto.\n" //Imprime que el enemigo ha muerto

                var levelUp = player.receiveExp(enemy.exp!!) //Recibe experiencia el jugador
                result += "${player.nombre} ha ganado ${enemy.exp} de experiencia.\n" //Imprime la experiencia Canada

                player.receiveCoins(enemy.monedas!!) //Recibe monedas el jugador
                result += "${player.nombre} ha ganado ${enemy.monedas} monedas.\n" //Imprime las monedas empanadas

                if (levelUp){ //Si el jugador sube de nivel
                    result += "${player.nombre} ha subido de nivel.\n" //Imprime que el jugador ha subido de nivel
                    player.levelUp() //Sube de nivel el jugador

                    //result datos del jugador
                    result += "Nombre: " + player.nombre + "\n"
                    result += "Nivel: " + player.nivel.toString() + "\n"
                    result += "HP: " + player.hp.toString() + "\n"
                    result += "ATK: " + player.atk.toString() + "\n"
                    result += "DEF: " + player.def.toString() + "\n"
                    result += "SPD: " + player.spd.toString() + "\n"
                }

                return result
            }
        }

        //Turno del enemigo
        if(enemy.posicion!! >= timeline) {
            enemy.posicion = 0 //Resetea la posicion del enemigo
            result += "${enemy.nombre} realizara una accion\n" //Imprime que comienza la batalla

            damage = enemy.attack(player) //Ataca el enemigo
            player.receiveDamage(damage) //Recibe daño el jugador
            result += "${enemy.nombre} ataca a ${player.nombre} y le hace $damage de daño.\n${player.nombre} tiene ${player.hp} de vida.\n" //Imprime el resultado del turno

            if(player.hp!! <= 0){ //Si el jugador muere
                result += "${player.nombre} ha muerto.\n" //Imprime que el jugador ha muerto

                //Castigo del jugador
                var coins = player.loseCoins(175 * player.nivel!!) //Pierde todas las monedas
                result += "${player.nombre} ha perdido $coins monedas.\n" //Imprime que el jugador ha perdido todas sus monedas
                player.revive() //Revive el jugador
                enemy = Enemigo().generateEnemy(player.nivel!!) //Genera un nuevo enemigo

                return result
            }
        }

        return result
    }

}