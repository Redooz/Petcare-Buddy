package com.example.petcarebuddy.views.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcarebuddy.repository.AuthRepository
import com.example.petcarebuddy.repository.BaseRepository
import com.example.petcarebuddy.views.auth.AuthViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw java.lang.IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}