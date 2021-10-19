package com.example.newsapp.domain.interactor

import com.example.newsapp.domain.model.Profile
import com.example.newsapp.domain.repository.MeRepository
import com.example.newsapp.domain.usecase.GetProfileUseCase
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

class ProfileInteractor(private val repository: MeRepository): GetProfileUseCase {
    override suspend fun getProfile() = repository.getProfile()
}