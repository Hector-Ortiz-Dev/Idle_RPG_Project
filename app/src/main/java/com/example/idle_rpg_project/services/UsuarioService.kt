package com.example.idle_rpg_project.services

import com.example.idle_rpg_project.builders.ServiceBuilder
import com.example.idle_rpg_project.interfaces.ApiUsuario
import com.example.idle_rpg_project.modelRequests.RequestUsuario
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
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}