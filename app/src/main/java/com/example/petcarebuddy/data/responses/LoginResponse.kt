package com.example.petcarebuddy.data.responses

data class LoginResponse(
    val access_token: String,
    val user: User
)