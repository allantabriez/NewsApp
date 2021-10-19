package com.example.newsapp.domain.repository

import com.example.newsapp.domain.usecase.GetNewsUseCase
import com.example.newsapp.domain.usecase.GetProfileUseCase

class MeInteractor(private val repository: MeRepository): GetNewsUseCase, GetProfileUseCase {
    override suspend fun getNews() = repository.getNews()

    override suspend fun getProfile() = repository.getProfile()
}