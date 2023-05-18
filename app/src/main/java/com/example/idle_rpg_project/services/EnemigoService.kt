package com.example.idle_rpg_project.services

import com.example.idle_rpg_project.builders.ServiceBuilder
import com.example.idle_rpg_project.interfaces.ApiEnemigo
import com.example.idle_rpg_project.modelRequests.RequestEnemigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnemigoService {
    fun getAll(onResult: (RequestEnemigo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiEnemigo::class.java)
        retrofit.getAll().enqueue(
            object : Callback<RequestEnemigo> {
                override fun onFailure(call: Call<RequestEnemigo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestEnemigo>, response: Response<RequestEnemigo>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getById(id: Int, onResult: (RequestEnemigo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiEnemigo::class.java)
        retrofit.getById(id).enqueue(
            object : Callback<RequestEnemigo> {
                override fun onFailure(call: Call<RequestEnemigo>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestEnemigo>, response: Response<RequestEnemigo>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }
}