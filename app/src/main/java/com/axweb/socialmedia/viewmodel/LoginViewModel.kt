package com.axweb.socialmedia.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axweb.socialmedia.model.LoginResponse
import com.axweb.socialmedia.model.User
import com.axweb.socialmedia.repository.LoginRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel constructor(private val repository: LoginRepository) : ViewModel() {

    val success = MutableLiveData<User>()
    val erroMessage = MutableLiveData<String>()

    fun login(user: User) {
        Log.i("TAG", user.username)
        Log.i("TAG", user.password)

        repository.login(user).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                success.postValue(user)
                Log.i("TAG", "onResponse: ")
                Log.i("TAG", response.body().toString())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                erroMessage.postValue(t.message)
            }

        })
    }
}