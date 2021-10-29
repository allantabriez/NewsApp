package com.example.newsapp.presentation.start

import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.usecase.IsLoggedInUseCase

class StartViewModel(private val useCase: IsLoggedInUseCase): ViewModel() {

    fun isLoggedIn() = useCase.invoke()
}