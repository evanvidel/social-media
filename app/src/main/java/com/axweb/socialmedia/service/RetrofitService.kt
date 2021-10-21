package com.axweb.socialmedia.service

import com.axweb.socialmedia.model.LoginResponse
import com.axweb.socialmedia.model.User
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @POST("/api/login")
    fun login(@Body user: User):Call<LoginResponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://demo-social-media-api.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(logger)
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        private val logger: OkHttpClient
            get() {
                val httpClient = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)

                return httpClient.build()
            }

        fun getInstance(): RetrofitService{
            return retrofitService
        }
    }
}