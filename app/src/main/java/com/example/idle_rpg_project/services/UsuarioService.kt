package com.example.idle_rpg_project.services

import com.example.idle_rpg_project.builders.ServiceBuilder
import com.example.idle_rpg_project.interfaces.ApiUsuario
import com.example.idle_rpg_project.modelRequests.RequestUsuario
import com.example.idle_rpg_project.models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioService {
    fun getAll(onResult: (RequestUsuario?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiUsuario::class.java)
        retrofit.getAll().enqueue(
            object : Callback<RequestUsuario> {
                override fun onFailure(call: Call<RequestUsuario>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestUsuario>, response: Response<RequestUsuario>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }

    fun getById(id: Int, onResult: (RequestUsuario?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiUsuario::class.java)
        retrofit.getById(id).enqueue(
            object : Callback<RequestUsuario> {
                override fun onFailure(call: Call<RequestUsuario>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<RequestUsuario>, response: Response<RequestUsuario>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }


    fun post(data: Usuario, onResult: (RequestUsuario?) -> Unit){
        val retrofit = ServiceBuilder.buildService(ApiUsuario::class.java)
        retrofit.post(data).enqueue(
            object : Callback<RequestUsuario> {
                override fun onFailure(call: Call<RequestUsuario>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<RequestUsuario>, response: Response<RequestUsuario>) {
                    val result = response.body()
                    onResult(result)
                }
            }
        )
    }
}