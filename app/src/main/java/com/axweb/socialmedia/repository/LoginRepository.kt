package com.axweb.socialmedia.repository

import com.axweb.socialmedia.model.LoginRequest
import com.axweb.socialmedia.service.RetrofitService

class LoginRepository constructor(private val retrofitService: RetrofitService){

    fun login(user: LoginRequest) = retrofitService.login(user)

}