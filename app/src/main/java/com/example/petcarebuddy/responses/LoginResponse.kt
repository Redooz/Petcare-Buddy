package com.example.petcarebuddy.responses

data class LoginResponse(
    val access_token: String,
    val user: User
)