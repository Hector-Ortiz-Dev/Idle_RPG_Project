package com.example.idle_rpg_project.models

import java.io.Serializable

data class Jugador(
    val method:String? = null,
    val id:Int? = null,
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
}