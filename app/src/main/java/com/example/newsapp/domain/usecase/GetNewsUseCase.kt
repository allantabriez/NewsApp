package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.repository.MeRepository

class GetNewsUseCase(private val repository: MeRepository) {
    suspend operator fun invoke() = repository.getNews()
}