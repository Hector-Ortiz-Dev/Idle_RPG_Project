package com.example.idle_rpg_project.interfaces

import com.example.idle_rpg_project.modelRequests.RequestEnemigo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiEnemigo {
    @GET("enemigo")
    abstract fun getAll(): Call<RequestEnemigo>

    @GET("enemigo/{id}")
    abstract fun getById(@Path("id") id: Int): Call<RequestEnemigo>
}