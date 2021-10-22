package com.axweb.socialmedia.service

import com.axweb.socialmedia.model.LoginResponse
import com.axweb.socialmedia.model.LoginRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitService {

    @POST("/api/login/")
    fun login(@Body request: LoginRequest):Call<LoginResponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)


            val retrofit = Retrofit.Builder()
                .baseUrl("https://demo-social-media-api.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

            retrofit.create(RetrofitService::class.java)
        }



        fun getInstance(): RetrofitService{
            return retrofitService
        }
    }
}