package com.example.petcarebuddy.data.responses

data class CreateUpdatePetResponse(
    val age: Int,
    val description: String,
    val id: Int,
    val name: String,
    val pathologies: String,
    val type: String,
    val user: UserX
)