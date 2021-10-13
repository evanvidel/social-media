package com.axweb.socialmedia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axweb.socialmedia.R
import com.axweb.socialmedia.databinding.ActivityFormLoginBinding

class FormLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
    }
}