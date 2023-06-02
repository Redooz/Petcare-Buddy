package com.example.petcarebuddy.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.petcarebuddy.data.network.PetAPI
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.PetRepository
import com.example.petcarebuddy.data.responses.GetPetsResponse
import com.example.petcarebuddy.databinding.FragmentHomeBinding
import com.example.petcarebuddy.views.base.BaseFragment
import com.example.petcarebuddy.views.pet.CreatePetActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, PetRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPets()

        viewModel.getPetsResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    //Add a textview from each value to the pets chooser
                    val pets = it.value

                    addPetsToChooser(pets)
                }
                is Resource.Failure -> Toast.makeText(
                    requireContext(),
                    "Retrieving Pets Failure",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        binding.createReminderBtn.setOnClickListener {
            //CreateReminderActivity
        }

        binding.addNewPetBtn.setOnClickListener {
            startActivity(Intent(activity, CreatePetActivity::class.java))
        }

        binding.emergencyBtn.setOnClickListener {
            //EmergencyActivity
        }

        binding.tipsBtn.setOnClickListener {
            //TipsActivity
        }
    }

    private fun addPetsToChooser(pets: GetPetsResponse) {
        binding.petsChooser.removeAllViews()


        if (pets.isEmpty()) {
            Toast.makeText(requireContext(), "You don't have pets", Toast.LENGTH_SHORT).show()
        }

        pets.forEach { pet ->
            val fragment = PetDetailsFragment.newInstance(pet.name, pet.age)

            childFragmentManager.beginTransaction().add(binding.petsChooser.id, fragment).commit()
        }
    }

    override fun getViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): PetRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(PetAPI::class.java, token)
        return PetRepository(api)
    }
}