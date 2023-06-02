package com.example.petcarebuddy.views.reminder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.ReminderRepository
import com.example.petcarebuddy.data.responses.CreateReminderResponse
import kotlinx.coroutines.launch

class CreateReminderViewModel(
    private val repository: ReminderRepository
): ViewModel() {
    private val _createReminderResponse: MutableLiveData<Resource<CreateReminderResponse>> = MutableLiveData()
    val createReminderResponse: LiveData<Resource<CreateReminderResponse>>
        get() = _createReminderResponse

    fun createReminder(
        title: String,
        body: String,
        date: String,
    ) = viewModelScope.launch {
        _createReminderResponse.value = repository.createReminder(title, body, date)
        println(_createReminderResponse.value)
    }
}