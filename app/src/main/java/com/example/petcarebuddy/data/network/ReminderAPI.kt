package com.example.petcarebuddy.data.network

import com.example.petcarebuddy.data.responses.*
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ReminderAPI {
    @FormUrlEncoded
    @POST("reminders")
    suspend fun createReminder(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("date") date: String,
    ) : CreateReminderResponse
}