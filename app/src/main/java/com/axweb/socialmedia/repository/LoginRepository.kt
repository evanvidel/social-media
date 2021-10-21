package com.axweb.socialmedia.repository

import com.axweb.socialmedia.model.User
import com.axweb.socialmedia.service.RetrofitService

class LoginRepository constructor(private val retrofitService: RetrofitService){

    fun login(user: User) = retrofitService.login(user)

}