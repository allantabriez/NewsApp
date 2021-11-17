package com.example.newsapp.di

import com.example.newsapp.presentation.home.HomeViewModel
import com.example.newsapp.presentation.login.LoginViewModel
import com.example.newsapp.presentation.start.StartViewModel
import com.example.newsapp.utils.EspressoIdlingResource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(get(), {
            EspressoIdlingResource.increment()
        }, {
            EspressoIdlingResource.decrement()
        })
    }
    viewModel { StartViewModel(get()) }
    viewModel {
        HomeViewModel(get(), get(), {
            EspressoIdlingResource.increment()
        }, {
            EspressoIdlingResource.decrement()
        })
    }
}