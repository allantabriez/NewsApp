package com.example.newsapp.domain.usecase

import com.example.newsapp.domain.model.Profile
import com.example.newsapp.utils.Resource

interface GetProfileUseCase {
    suspend fun getProfile(): Resource<Profile>
}