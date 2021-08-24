package com.example.heroes.di

import com.example.heroes.repository.MainRepository
import com.example.heroes.ui.heroes.ListHeroesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        MainRepository()
    }
    viewModel {
        ListHeroesViewModel(
            repository = get()
        )
    }
}