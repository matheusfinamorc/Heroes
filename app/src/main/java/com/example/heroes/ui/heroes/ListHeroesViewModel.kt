package com.example.heroes.ui.heroes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.heroes.model.HeroesItem
import com.example.heroes.repository.MainRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class ListHeroesViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val mSearchResponse: MutableLiveData<Response<List<HeroesItem>>> = MutableLiveData()

    fun getSearch(keyword: String) {
        viewModelScope.launch {
            val response = repository.getHeroesSearch(keyword)
            mSearchResponse.value = response
        }
    }
}