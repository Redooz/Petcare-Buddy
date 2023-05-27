package com.example.petcarebuddy.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petcarebuddy.R
import com.example.petcarebuddy.databinding.ActivityHelloBinding
import com.example.petcarebuddy.databinding.ActivityHomeBinding
import com.example.petcarebuddy.views.auth.AuthActivity

class HelloActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            // Send to login fragment
            startActivity(Intent(this@HelloActivity, AuthActivity::class.java))
        }

        binding.createAccountBtn.setOnClickListener {
            // Send to register fragment
            startActivity(Intent(this@HelloActivity, AuthActivity::class.java))
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}