package com.example.petcarebuddy.views.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.example.petcarebuddy.databinding.FragmentLoginBinding
import com.example.petcarebuddy.data.network.AuthAPI
import com.example.petcarebuddy.data.network.Resource
import com.example.petcarebuddy.data.repository.AuthRepository
import com.example.petcarebuddy.views.base.BaseFragment
import com.example.petcarebuddy.views.enable
import com.example.petcarebuddy.views.home.HomeActivity
import com.example.petcarebuddy.views.startNewActivity
import com.example.petcarebuddy.views.visible

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)
        binding.loginBtn.enable(false)

        viewModel.enablePushResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> Toast.makeText(requireContext(), "Enabling Notifications Failure", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(false)
            when (it) {
                is Resource.Success -> {
                    viewModel.saveAuthToken(it.value.access_token)
                    viewModel.enablePush(it.value.user.id, "Android")
                }
                is Resource.Failure -> Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
            }
        })

        binding.passwordTxt.addTextChangedListener {
            val email = binding.emailTxt.text.toString().trim()
            binding.loginBtn.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.emailTxt.text.toString().trim()
            val password = binding.passwordTxt.text.toString().trim()

            binding.progressBar.visible(true)
            viewModel.login(email, password)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthAPI::class.java), userPreferences)

}