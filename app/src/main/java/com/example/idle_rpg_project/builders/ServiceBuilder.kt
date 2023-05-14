package com.example.idle_rpg_project.builders

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient().newBuilder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://movilesmx.000webhostapp.com/idle_rpg/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}