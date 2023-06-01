package com.example.petcarebuddy.views.auth

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.AuthRepository
import com.example.petcarebuddy.data.responses.EnablePushResponse
import com.example.petcarebuddy.data.responses.LoginResponse
import com.example.petcarebuddy.views.HelloActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {

    private val _loginResponse : MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    private val _enablePushResponse : MutableLiveData<Resource<EnablePushResponse>> = MutableLiveData()
    val enablePushResponse: LiveData<Resource<EnablePushResponse>>
        get() = _enablePushResponse

    private var token: String? = null

    init {
        // Retrieve Firebase Messaging token
        retrieveFBCToken()
    }

    private fun retrieveFBCToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("AuthViewModel", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            token = task.result

            // Log
            Log.d("AuthViewModel", token!!)
        })
    }

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = repository.login(email, password)
    }

    fun enablePush(
        id: Int,
        deviceType: String,
    ) = viewModelScope.launch {
        _enablePushResponse.value = repository.enablePush(id, deviceType, token!!)
    }

    fun saveAuthToken(token: String)  = viewModelScope.launch{
        repository.saveAuthToken(token)
    }
}