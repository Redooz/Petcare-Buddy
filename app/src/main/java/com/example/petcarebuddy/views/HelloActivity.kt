package com.example.petcarebuddy.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.petcarebuddy.MyFirebaseMessagingService
import com.example.petcarebuddy.R
import com.example.petcarebuddy.data.UserPreferences
import com.example.petcarebuddy.databinding.ActivityHelloBinding
import com.example.petcarebuddy.databinding.ActivityHomeBinding
import com.example.petcarebuddy.views.auth.AuthActivity
import com.example.petcarebuddy.views.auth.RegisterActivity
import com.example.petcarebuddy.views.home.HomeActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class HelloActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHelloBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
            if (it != null) {
                val activity = HomeActivity::class.java
                startNewActivity(activity)
            }
        })

        binding.loginBtn.setOnClickListener {
            // Send to login fragment
            startNewActivity(AuthActivity::class.java)
        }

        binding.createAccountBtn.setOnClickListener {
            // Send to register fragment
            startNewActivity(RegisterActivity::class.java)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}