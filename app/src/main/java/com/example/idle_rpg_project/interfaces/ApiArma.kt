package com.example.idle_rpg_project.interfaces

import com.example.idle_rpg_project.modelRequests.RequestArma
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiArma {
    @GET("arma")
    abstract fun getAll(): Call<RequestArma>

    @GET("arma/{id}")
    abstract fun getById(@Path("id") id: Int): Call<RequestArma>
}