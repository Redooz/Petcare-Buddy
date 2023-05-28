package com.example.petcarebuddy.views.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.AuthRepository
import com.example.petcarebuddy.data.responses.RegisterResponse
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository
): ViewModel() {
    private val _registerResponse : MutableLiveData<Resource<RegisterResponse>> = MutableLiveData()
    val registerResponse: LiveData<Resource<RegisterResponse>>
        get() = _registerResponse

    fun register(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _registerResponse.value = repository.register(email, password)
    }
}