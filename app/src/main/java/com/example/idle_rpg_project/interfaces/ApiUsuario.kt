package com.example.idle_rpg_project.interfaces

import com.example.idle_rpg_project.modelRequests.RequestUsuario
import com.example.idle_rpg_project.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiUsuario {
    @GET("usuario")
    abstract fun getAll(): Call<RequestUsuario>

    @POST("usuario")
    abstract fun login(@Body data: Usuario): Call<RequestUsuario>
}