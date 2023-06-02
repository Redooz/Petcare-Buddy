package com.example.petcarebuddy.data.repository

import com.example.petcarebuddy.data.network.ReminderAPI
import retrofit2.http.FormUrlEncoded

class ReminderRepository(
    private val api: ReminderAPI,
) : BaseRepository() {

    @FormUrlEncoded
    suspend fun createReminder(
        title: String,
        body: String,
        date: String,
    ) = safeApiCall {
        api.createReminder(title, body, date)
    }

}