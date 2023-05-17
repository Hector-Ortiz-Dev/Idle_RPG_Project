package com.example.idle_rpg_project.interfaces

import com.example.idle_rpg_project.modelRequests.RequestUsuario
import com.example.idle_rpg_project.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUsuario {
    @GET("usuario")
    abstract fun getAll(): Call<RequestUsuario>

    @GET("usuario/{id}")
    abstract fun getById(@Path("id") id: Int): Call<RequestUsuario>

    @POST("usuario")
    abstract fun post(@Body data: Usuario): Call<RequestUsuario>
}