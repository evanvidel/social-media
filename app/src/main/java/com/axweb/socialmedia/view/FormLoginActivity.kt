package com.axweb.socialmedia.view

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.axweb.socialmedia.databinding.ActivityFormLoginBinding
import com.axweb.socialmedia.model.LoginRequest
import com.axweb.socialmedia.repository.LoginRepository
import com.axweb.socialmedia.service.RetrofitService
import com.axweb.socialmedia.viewmodel.LoginViewModel


class FormLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding
    private var viewModel: LoginViewModel = LoginViewModel(LoginRepository(RetrofitService.getInstance()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.etEmail.addTextChangedListener(textWatcher)
        binding.etPassword.addTextChangedListener(textWatcher)

        binding.btLogin.setOnClickListener {

            singIn()
        }



        initObservables()

       @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

    }
    fun singIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.login(

            LoginRequest(email, password)
        )
    }

   fun initObservables(){
       viewModel.erroMessage.observe(this,{
           Toast.makeText(this, it ,Toast.LENGTH_SHORT).show()
       })

       viewModel.success.observe(this,{
           Toast.makeText(this,"Sucesso: $it",Toast.LENGTH_LONG).show()
       })
    }

   var textWatcher: TextWatcher =  object : TextWatcher{
       override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

       }

       override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
           val email = binding.etEmail.text.toString()
           val password = binding.etPassword.text.toString()

           if(email.isNotEmpty() && password.isNotEmpty()) {
               binding.btLogin.setEnabled(true)

           }else{
               binding.btLogin.setEnabled(false)
           }
       }

       override fun afterTextChanged(s: Editable?) {

       }

   }
}