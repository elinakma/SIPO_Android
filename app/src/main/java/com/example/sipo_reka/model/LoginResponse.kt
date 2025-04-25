package com.example.sipo_reka.model

data class LoginResponse(
    val message: String,
    val token: String,
    val user: UserResponse
)

data class UserResponse(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val password: String,
    val phone_number: String,
    val role: String,
    val position_id_position: Int,
    val divisi_id_divisi: Int
)
