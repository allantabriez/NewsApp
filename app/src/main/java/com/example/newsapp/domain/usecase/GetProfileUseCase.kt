package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.Profile
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase {
    suspend fun getProfile(): Flow<Resource<Profile>>
}