package com.example.heroes.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://superheroapi.com/api/"

class AppRetrofit {
    val client = OkHttpClient.Builder()
        .addInterceptor(AppInterceptor())
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    val heroesService: API by lazy {
        retrofit.create(API::class.java)
    }
}