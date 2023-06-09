package com.example.petcarebuddy.views.home

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.petcarebuddy.databinding.FragmentPetDetailsBinding

private const val ARG_TXT_VIEW_NAME = "param1"
private const val ARG_TXT_VIEW_AGE = "param2"

class PetDetailsFragment : Fragment() {
    private var paramName: String? = null
    private var paramAge: String? = null
    private var _binding: FragmentPetDetailsBinding? = null
    private val binding get() = _binding!!

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramName = it.getString(ARG_TXT_VIEW_NAME)
            paramAge = it.getString(ARG_TXT_VIEW_AGE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.petAgeTxt.text = "Age: $paramAge"
        binding.petNameTxt.text = paramName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, age: Int) =
            PetDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TXT_VIEW_NAME, name)
                    putString(ARG_TXT_VIEW_AGE, age.toString())
                }
            }
    }
}