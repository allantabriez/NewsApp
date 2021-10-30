package com.example.newsapp.data

import com.example.newsapp.data.local.entity.toModel
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.model.Profile
import com.example.newsapp.domain.repository.MeRepository
import com.example.newsapp.utils.ErrorCode
import com.example.newsapp.utils.NetworkThrowable

class MeRepositoryImpl(
    private val localSource: MeDataSource,
    private val remoteSource: MeDataSource,
) : MeRepository {
    override suspend fun getNews(): List<News> {
        return try {
            val remoteResponse = remoteSource.getNews()
            localSource.deleteCachedNews()
            localSource.insertNews(remoteResponse.map { it.toEntity() })
            remoteResponse.map { it.toModel() }
        } catch (e: NetworkThrowable) {
            if (e.errorCode == ErrorCode.Code401) throw e
            else {
                val localResponse = localSource.getCachedNews()
                if (localResponse.isEmpty()) throw e
                else localResponse.map { it.toModel() }
            }
        }
    }

    override suspend fun getProfile(): Profile {
        return try {
            val remoteResponse = remoteSource.getProfile()
            localSource.deleteCachedProfile()
            localSource.insertProfile(remoteResponse.toEntity())
            remoteResponse.toModel()
        } catch (e: NetworkThrowable) {
            if (e.errorCode == ErrorCode.Code401) throw e
            else {
                val localResponse = localSource.getCachedProfile()
                if (localResponse.isEmpty()) throw e
                else localResponse[0].toModel()
            }
        }
    }
}