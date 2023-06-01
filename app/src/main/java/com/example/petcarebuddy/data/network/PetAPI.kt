package com.example.petcarebuddy.data.network

import com.example.petcarebuddy.data.responses.*
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PetAPI {
    @GET("pets")
    suspend fun allPets() : GetPetsResponse

    @GET("pets/{id}")
    suspend fun onePet(
        @Path("id") id: Int,
    ) : GetOnePetResponse

    @FormUrlEncoded
    @POST("pets")
    suspend fun createPet(
        @Field("age") age: Int,
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("pathologies") pathologies: String,
        @Field("type") type: String
    ) : CreateUpdatePetResponse

    @FormUrlEncoded
    @PATCH("pets/{id}")
    suspend fun updatePet(
        @Path("id") id: Int,
        @Field("age") age: Int,
        @Field("name") name: String,
        @Field("description") description: String,
        @Field("pathologies") pathologies: String,
        @Field("type") type: String
    ) : CreateUpdatePetResponse

    @DELETE("pets/{id}")
    suspend fun deletePet(
        @Path("id") id: Int,
    ) : DeletePetResponse
}