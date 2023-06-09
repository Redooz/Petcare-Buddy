package com.example.petcarebuddy.data.responses

data class EnablePushResponse(
    val device_type: String,
    val id: Int,
    val notification_token: String,
    val status: String,
    val user: UserX
)