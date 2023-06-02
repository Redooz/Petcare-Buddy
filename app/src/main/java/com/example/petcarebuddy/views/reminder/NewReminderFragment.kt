package com.example.petcarebuddy.views.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.petcarebuddy.data.network.PetAPI
import com.example.petcarebuddy.data.network.ReminderAPI
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.PetRepository
import com.example.petcarebuddy.data.repository.ReminderRepository
import com.example.petcarebuddy.databinding.FragmentNewReminderBinding
import com.example.petcarebuddy.views.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class NewReminderFragment : BaseFragment<CreateReminderViewModel, FragmentNewReminderBinding, ReminderRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.timePicker.setIs24HourView(true)

        viewModel.createReminderResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> Toast.makeText(requireContext(), "Creating Reminder Succesfully", Toast.LENGTH_SHORT).show()
                is Resource.Failure -> Toast.makeText(requireContext(), "Creating Reminder Failure", Toast.LENGTH_SHORT).show()
                is Resource.Wtf -> Toast.makeText(requireContext(), "Internal Failure", Toast.LENGTH_SHORT).show()
            }
        })

        binding.createBtn.setOnClickListener {
            val title = binding.titleTxt.text.toString().trim()
            val body = binding.textAreaDescriptionTxt.text.toString().trim()
            val month = binding.datePicker.month
            val day = binding.datePicker.dayOfMonth
            val hour = binding.timePicker.hour
            val minute = binding.timePicker.minute
            val cronDate = "$minute 0$hour 0$day $month *"

            println("$title, $body, $cronDate")

            viewModel.createReminder(title, body, cronDate)
        }
    }

    override fun getViewModel() = CreateReminderViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewReminderBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): ReminderRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(ReminderAPI::class.java, token)
        return ReminderRepository(api)
    }

}