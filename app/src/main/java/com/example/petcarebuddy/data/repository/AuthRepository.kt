package com.example.petcarebuddy.data.repository

import com.example.petcarebuddy.data.UserPreferences
import com.example.petcarebuddy.data.network.AuthAPI

class AuthRepository(
    private val api: AuthAPI,
    private val preferences: UserPreferences
) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun register(
        email: String,
        password: String
    ) = safeApiCall {
        api.register(email, password)
    }

    suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }
}