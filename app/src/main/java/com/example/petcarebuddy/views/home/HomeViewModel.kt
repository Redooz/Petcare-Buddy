package com.example.petcarebuddy.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.PetRepository
import com.example.petcarebuddy.data.responses.GetPetsResponse
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PetRepository
): ViewModel() {
    private val _getPetsResponse: MutableLiveData<Resource<GetPetsResponse>> = MutableLiveData()
    val getPetsResponse: LiveData<Resource<GetPetsResponse>>
        get() = _getPetsResponse

    fun getPets() = viewModelScope.launch {
        _getPetsResponse.value = repository.allPets()
    }
}