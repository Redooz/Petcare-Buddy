package com.example.petcarebuddy.data.network

import com.example.petcarebuddy.data.responses.LoginResponse
import com.example.petcarebuddy.data.responses.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : LoginResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String
    ) : RegisterResponse
}