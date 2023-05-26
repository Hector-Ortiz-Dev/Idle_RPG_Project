package com.example.idle_rpg_project.models

import java.io.Serializable

data class Usuario(
    val method:String? = null,
    val id:Int? = null,
    val username:String? = null,
    val nombre:String? = null,
    val apellidos:String? = null,
    val correo:String? = null,
    val contrasena:String? = null,
    val urlImagen:String? = null,
    val idGremio:Int? = null,
    val espera:Int? = null,
    val lider:Int? = null,
    val idUsuario:Int? = null) : Serializable {
}