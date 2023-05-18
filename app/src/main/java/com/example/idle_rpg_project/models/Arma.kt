package com.example.idle_rpg_project.models

import java.io.Serializable

data class Arma(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = null,
    val atk:Int? = null) : Serializable {
}