package com.example.petcarebuddy.data.responses

data class RegisterResponse(
    val email: String,
    val id: Int,
    val password: String,
    val status: String
)