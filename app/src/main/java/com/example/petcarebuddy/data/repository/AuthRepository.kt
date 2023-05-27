package com.example.petcarebuddy.repository

import com.example.petcarebuddy.network.AuthAPI

class AuthRepository(
    private val api: AuthAPI
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }
}