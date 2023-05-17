package com.example.idle_rpg_project.services

import com.example.idle_rpg_project.builders.ServiceBuilder
import com.example.idle_rpg_project.interfaces.ApiJugador
import com.example.idle_rpg_project.modelRequests.RequestJugador
import com.example.idle_rpg_project.models.Jugador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JugadorService {
    fun getAll(onResult: (RequestJugador?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiJugador::class.java)
        retrofit.getAll().enqueue(
            object : Callback<RequestJugador> {
                override fun onFailure(call: Call<RequestJugador>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestJugador>, response: Response<RequestJugador>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getById(id: Int, onResult: (RequestJugador?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiJugador::class.java)
        retrofit.getById(id).enqueue(
            object : Callback<RequestJugador> {
                override fun onFailure(call: Call<RequestJugador>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestJugador>, response: Response<RequestJugador>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getNextLevel(level: Int, onResult: (RequestJugador?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiJugador::class.java)
        retrofit.getNextLevel(level).enqueue(
            object : Callback<RequestJugador> {
                override fun onFailure(call: Call<RequestJugador>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestJugador>, response: Response<RequestJugador>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun post(data: Jugador, onResult: (RequestJugador?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiJugador::class.java)
        retrofit.post(data).enqueue(
            object : Callback<RequestJugador> {
                override fun onFailure(call: Call<RequestJugador>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestJugador>, response: Response<RequestJugador>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }
}