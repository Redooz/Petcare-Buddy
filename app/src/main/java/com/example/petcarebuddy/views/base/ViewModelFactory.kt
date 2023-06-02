package com.example.petcarebuddy.views.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.petcarebuddy.data.repository.AuthRepository
import com.example.petcarebuddy.data.repository.BaseRepository
import com.example.petcarebuddy.data.repository.PetRepository
import com.example.petcarebuddy.data.repository.ReminderRepository
import com.example.petcarebuddy.views.auth.AuthViewModel
import com.example.petcarebuddy.views.auth.RegisterViewModel
import com.example.petcarebuddy.views.home.HomeViewModel
import com.example.petcarebuddy.views.pet.CreatePetViewModel
import com.example.petcarebuddy.views.reminder.CreateReminderViewModel

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as PetRepository) as T
            modelClass.isAssignableFrom(CreatePetViewModel::class.java) -> CreatePetViewModel(repository as PetRepository) as T
            modelClass.isAssignableFrom(CreateReminderViewModel::class.java) -> CreateReminderViewModel(repository as ReminderRepository) as T
            else -> throw java.lang.IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}