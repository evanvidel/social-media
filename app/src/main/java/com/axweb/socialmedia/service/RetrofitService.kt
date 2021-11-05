package com.axweb.socialmedia.service

import com.axweb.socialmedia.model.LoginResponse
import com.axweb.socialmedia.model.LoginRequest
import com.axweb.socialmedia.model.SessionHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import okhttp3.Interceptor
import okhttp3.Request
import retrofit2.Response
import java.io.IOException


interface RetrofitService {

    @POST("/api/login/")
    fun login(@Body request: LoginRequest):Call<LoginResponse>

    companion object {

        private val retrofitService: RetrofitService by lazy {

            val logging = HttpLoggingInterceptor()
            logging.apply { logging.level = HttpLoggingInterceptor.Level.BODY }
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            httpClient.interceptors().add(Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = chain.request().newBuilder()

                if (!SessionHelper.token.isNullOrEmpty()) {
                    requestBuilder.header("token", SessionHelper.token ?: "" )
                }
                if (!SessionHelper.pais.isNullOrEmpty()) {
                    requestBuilder.header("country", SessionHelper.pais ?: "")
                }
                requestBuilder.method(original.method, original.body)
                val response = chain.proceed(requestBuilder.build())

                response
            })




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