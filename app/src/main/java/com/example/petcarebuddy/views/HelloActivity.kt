package com.example.petcarebuddy.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.petcarebuddy.R

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}