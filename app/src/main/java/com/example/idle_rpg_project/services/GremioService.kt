package com.example.idle_rpg_project.services

import com.example.idle_rpg_project.builders.ServiceBuilder
import com.example.idle_rpg_project.interfaces.ApiGremio
import com.example.idle_rpg_project.modelRequests.RequestGremio
import com.example.idle_rpg_project.models.Gremio
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GremioService {
    fun getAll(onResult: (RequestGremio?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiGremio::class.java)
        retrofit.getAll().enqueue(
            object : Callback<RequestGremio> {
                override fun onFailure(call: Call<RequestGremio>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestGremio>, response: Response<RequestGremio>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getById(id: Int, onResult: (RequestGremio?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiGremio::class.java)
        retrofit.getById(id).enqueue(
            object : Callback<RequestGremio> {
                override fun onFailure(call: Call<RequestGremio>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestGremio>, response: Response<RequestGremio>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getNextLevel(level: Int, onResult: (RequestGremio?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiGremio::class.java)
        retrofit.getNextLevel(level).enqueue(
            object : Callback<RequestGremio> {
                override fun onFailure(call: Call<RequestGremio>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestGremio>, response: Response<RequestGremio>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getMiembrosPendientes(level: Int, onResult: (RequestGremio?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiGremio::class.java)
        retrofit.getMiembrosPendientes(level).enqueue(
            object : Callback<RequestGremio> {
                override fun onFailure(call: Call<RequestGremio>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestGremio>, response: Response<RequestGremio>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun post(data: Gremio, onResult: (RequestGremio?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiGremio::class.java)
        retrofit.post(data).enqueue(
            object : Callback<RequestGremio> {
                override fun onFailure(call: Call<RequestGremio>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestGremio>, response: Response<RequestGremio>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }
}