package com.example.petcarebuddy.views.pet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.petcarebuddy.data.network.PetAPI
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.PetRepository
import com.example.petcarebuddy.databinding.FragmentCreatePetBinding
import com.example.petcarebuddy.views.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class CreatePetFragment : BaseFragment<CreatePetViewModel, FragmentCreatePetBinding, PetRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.createPetResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> Toast.makeText(requireContext(), "Creating Pet Succesfully", Toast.LENGTH_SHORT).show()
                is Resource.Failure -> Toast.makeText(requireContext(), "Creating Pet Failure", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        })


        binding.createBtn.setOnClickListener {
            val name = binding.nameTxt.text.toString().trim()
            val age = binding.ageTxt.text.toString().trim()
            val description = binding.descriptionTxt.text.toString().trim()
            val pathologies = binding.pathologiesTxt.text.toString().trim()
            val type = binding.typeTxt.text.toString().trim().lowercase()

            viewModel.createPet(age.toInt(), name, description, pathologies, type)
        }
    }

    override fun getViewModel(): Class<CreatePetViewModel> = CreatePetViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreatePetBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): PetRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(PetAPI::class.java, token)
        return PetRepository(api)
    }

}