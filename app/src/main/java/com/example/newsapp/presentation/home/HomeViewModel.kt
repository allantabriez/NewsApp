package com.example.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val newsUseCase: GetNewsUseCase,
    private val profileUseCase: GetProfileUseCase,
):ViewModel() {

    fun getNews() {
        viewModelScope.launch {
            runCatching {
                newsUseCase.invoke()
            }.onSuccess {

            }.onFailure {

            }
        }
    }
}