package com.example.idle_rpg_project.models

import java.io.Serializable

data class Gremio(
    val method:String? = null,
    val id:Int? = null,
    val nombre:String? = null,
    val cantidad:Int? = null,
    val nivel:Int? = null,
    val exp:Long? = null) : Serializable {
}