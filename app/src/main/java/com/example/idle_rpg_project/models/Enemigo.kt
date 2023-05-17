package com.example.idle_rpg_project.models

import java.io.Serializable

data class Enemigo(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = null,
    val hp:Int? = null,
    val atk:Int? = null,
    val def:Int? = null,
    val nivel:Int? = null) : Serializable {
}