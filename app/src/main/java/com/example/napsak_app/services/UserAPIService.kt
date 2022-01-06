package com.example.napsak_app.services

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://ameanacademy.herokuapp.com/api/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface UserAPIService{
    @Headers(
        "Content-Type: application/json;charset=utf-8"
    )

    @GET("user")
    fun getUser() : Call<String>
}

object UserAPI{
    val retrofitService: UserAPIService by lazy {
        retrofit.create(UserAPIService:: class.java)
    }
}