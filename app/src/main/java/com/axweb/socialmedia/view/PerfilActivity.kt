package com.axweb.socialmedia.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.axweb.socialmedia.databinding.ActivityPerfilBinding

class PerfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

    }
}