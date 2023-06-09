package com.example.petcarebuddy.data.repository

import android.util.Log
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

    suspend fun enablePush(
        id: Int,
        deviceType: String,
        notificationToken: String,
    ) = safeApiCall {
        Log.w("ENABLE PUSH", notificationToken)
        api.enablePush(id, deviceType, notificationToken)
    }
}