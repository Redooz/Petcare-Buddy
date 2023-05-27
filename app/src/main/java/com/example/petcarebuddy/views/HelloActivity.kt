package com.example.petcarebuddy.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petcarebuddy.R
import com.example.petcarebuddy.databinding.ActivityHomeBinding

class HelloActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}