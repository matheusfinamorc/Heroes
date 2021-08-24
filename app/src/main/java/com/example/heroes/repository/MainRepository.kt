package com.example.heroes.repository

import com.example.heroes.model.HeroesItem
import com.example.heroes.service.API
import com.example.heroes.service.AppRetrofit
import retrofit2.Response

class MainRepository(
    private val api: API = AppRetrofit().heroesService
) {
    suspend fun getHeroesSearch(keyword: String): Response<List<HeroesItem>>{
        return api.getHeroesSearch(keyword)
    }

}