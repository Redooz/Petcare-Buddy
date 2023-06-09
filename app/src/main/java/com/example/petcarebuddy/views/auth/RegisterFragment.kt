package com.example.petcarebuddy.views.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.petcarebuddy.R
import com.example.petcarebuddy.data.network.AuthAPI
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.AuthRepository
import com.example.petcarebuddy.databinding.FragmentLoginBinding
import com.example.petcarebuddy.databinding.FragmentRegisterBinding
import com.example.petcarebuddy.views.base.BaseFragment
import com.example.petcarebuddy.views.enable
import com.example.petcarebuddy.views.home.HomeActivity
import com.example.petcarebuddy.views.startNewActivity
import com.example.petcarebuddy.views.visible

class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding, AuthRepository>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.registerResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    requireActivity().startNewActivity(AuthActivity::class.java)
                }
                is Resource.Failure -> Toast.makeText(requireContext(), "Register Failure", Toast.LENGTH_SHORT).show()
                else -> {}
            }
        })

        binding.passwordTxt.addTextChangedListener {
            val email = binding.emailTxt.text.toString().trim()
        }

        binding.createAccountBtn.setOnClickListener {
            val email = binding.emailTxt.text.toString().trim()
            val password = binding.passwordTxt.text.toString().trim()

            viewModel.register(email, password)
        }
    }

    override fun getViewModel(): Class<RegisterViewModel> = RegisterViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentRegisterBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthAPI::class.java), userPreferences)

}