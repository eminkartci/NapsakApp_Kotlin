package com.example.napsak_app.services

import com.example.napsak_app.models.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://ameanacademy.herokuapp.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UserAPIService{
    @Headers(
        "Content-Type: application/json;charset=utf-8"
    )

    @GET("user")
    fun getUser() : Call<User>
}

object UserAPI{
    val retrofitService: UserAPIService by lazy {
        retrofit.create(UserAPIService:: class.java)
    }
}