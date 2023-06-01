package com.example.petcarebuddy.data.repository

import android.util.Log
import com.example.petcarebuddy.data.UserPreferences
import com.example.petcarebuddy.data.network.AuthAPI
import com.example.petcarebuddy.data.network.PetAPI
import retrofit2.http.Field
import retrofit2.http.Path

class PetRepository(
    private val api: PetAPI,
) : BaseRepository() {
    suspend fun onePet(
        id: Int
    ) = safeApiCall {
        api.onePet(id)
    }

    suspend fun createPet(
        age: Int,
        name: String,
        description: String,
        pathologies: String,
        type: String
    ) = safeApiCall {
        api.createPet(age, name, description, pathologies, type)
    }

    suspend fun updatePet(
        id: Int,
        age: Int,
        name: String,
        description: String,
        pathologies: String,
        type: String
    ) = safeApiCall {
        api.updatePet(id, age, name, description, pathologies, type)
    }

    suspend fun deletePet(
        id: Int
    ) = safeApiCall {
        api.deletePet(id)
    }
}