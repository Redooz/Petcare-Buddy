package com.example.petcarebuddy.views.pet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.PetRepository
import com.example.petcarebuddy.data.responses.CreateUpdatePetResponse
import kotlinx.coroutines.launch

class CreatePetViewModel(
    private val repository: PetRepository
): ViewModel() {
    private val _createPetResponse: MutableLiveData<Resource<CreateUpdatePetResponse>> = MutableLiveData()
    val createPetResponse: LiveData<Resource<CreateUpdatePetResponse>>
        get() = _createPetResponse

    fun createPet(
        age: Int,
        name: String,
        description: String,
        pathologies: String,
        type: String
    ) = viewModelScope.launch {
        _createPetResponse.value = repository.createPet(age, name, description, pathologies, type)
    }
}