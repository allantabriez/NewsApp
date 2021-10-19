package com.example.newsapp.domain.interactor

import com.example.newsapp.domain.repository.MeRepository
import com.example.newsapp.domain.usecase.GetNewsUseCase

class NewsInteractor(private val repository: MeRepository): GetNewsUseCase {
    override suspend fun getNews() = repository.getNews()
}