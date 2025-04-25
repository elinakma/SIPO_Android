package com.example.sipo_reka.data.network

import com.example.sipo_reka.model.LoginRequest
import com.example.sipo_reka.model.LoginResponse
import com.example.sipo_reka.model.Undangan
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// ApiService.kt
interface ApiService {
    @POST("api/v1/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("api/v1/undangan")
    suspend fun getUndangan(@Header("Authorization") token: String): List<Undangan>

    @GET("api/v1/undangan/superadmin")
    suspend fun getUndanganSuperadmin(@Header("Authorization") token: String): List<Undangan>

    @DELETE("api/v1/undangan/{id}")
    suspend fun deteleUndangan(@Header("Authorization") token: String, @Path("id") id: Int):
            Response<Unit>
}
