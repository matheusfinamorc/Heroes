package com.example.heroes.service

import com.example.heroes.model.HeroesItem
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("search/{name}")
    suspend fun getHeroesSearch(keyword: String
    ): Response<List<HeroesItem>>
}