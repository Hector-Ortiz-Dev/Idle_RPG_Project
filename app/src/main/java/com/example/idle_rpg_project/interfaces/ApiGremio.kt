package com.example.idle_rpg_project.interfaces

import com.example.idle_rpg_project.modelRequests.RequestGremio
import com.example.idle_rpg_project.models.Gremio
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiGremio {
    @GET("gremio")
    abstract fun getAll(): Call<RequestGremio>

    @GET("gremio/{id}")
    abstract fun getById(@Path("id") id: Int): Call<RequestGremio>

    @GET("gremio/next-level/{level}")
    abstract fun getNextLevel(@Path("level") level: Int): Call<RequestGremio>

    @GET("gremio/pendientes/{idGremio}")
    abstract fun getMiembrosPendientes(@Path("idGremio") idGremio: Int): Call<RequestGremio>

    @POST("gremio")
    abstract fun post(@Body data: Gremio): Call<RequestGremio>
}