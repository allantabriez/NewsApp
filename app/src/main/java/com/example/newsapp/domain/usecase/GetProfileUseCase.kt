package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.MeRepository

class GetProfileUseCase(private val repository: MeRepository) {
    suspend operator fun invoke() = repository.getNews()
}