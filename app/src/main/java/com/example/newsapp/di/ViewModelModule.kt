package com.example.newsapp.di

import com.example.newsapp.presentation.login.LoginViewModel
import com.example.newsapp.presentation.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { StartViewModel(get()) }
}