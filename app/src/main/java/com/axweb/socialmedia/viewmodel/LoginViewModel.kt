package com.axweb.socialmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axweb.socialmedia.model.LoginResponse
import com.axweb.socialmedia.model.LoginRequest
import com.axweb.socialmedia.repository.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel constructor(private val repository: LoginRepository) : ViewModel() {

    val success = MutableLiveData<String>()
    val erroMessage = MutableLiveData<String>()

    fun login(request: LoginRequest) {


        repository.login(request).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {


                if (response.body()?.token != null) {
                    success.postValue(response.body()?.token)
                }else {
                    erroMessage.postValue("No active account found with the given credentials")
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                erroMessage.postValue(t.message)
            }

        })
    }
}