package com.example.idle_rpg_project.interfaces

import com.example.idle_rpg_project.modelRequests.RequestJugador
import com.example.idle_rpg_project.models.Jugador
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiJugador {
    @GET("jugador")
    abstract fun getAll(): Call<RequestJugador>

    @GET("jugador/{id}")
    abstract fun getById(@Path("id") id: Int): Call<RequestJugador>

    @GET("jugador/next-level/{level}")
    abstract fun getNextLevel(@Path("level") level: Int): Call<RequestJugador>

    @POST("jugador")
    abstract fun post(@Body data: Jugador): Call<RequestJugador>
}