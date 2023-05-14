package com.example.idle_rpg_project.modelRequests

import com.example.idle_rpg_project.models.Usuario

class RequestUsuario(val estatus:Int, val mensaje:String, val detalle:String, val records:ArrayList<Usuario>) {
}