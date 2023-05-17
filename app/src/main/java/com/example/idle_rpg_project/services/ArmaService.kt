package com.example.idle_rpg_project.services

import com.example.idle_rpg_project.builders.ServiceBuilder
import com.example.idle_rpg_project.interfaces.ApiArma
import com.example.idle_rpg_project.modelRequests.RequestArma
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArmaService {
    fun getAll(onResult: (RequestArma?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiArma::class.java)
        retrofit.getAll().enqueue(
            object : Callback<RequestArma> {
                override fun onFailure(call: Call<RequestArma>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestArma>, response: Response<RequestArma>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getById(id: Int, onResult: (RequestArma?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiArma::class.java)
        retrofit.getById(id).enqueue(
            object : Callback<RequestArma> {
                override fun onFailure(call: Call<RequestArma>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestArma>, response: Response<RequestArma>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }
}