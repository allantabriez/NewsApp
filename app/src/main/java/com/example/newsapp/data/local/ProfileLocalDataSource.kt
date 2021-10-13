package com.example.newsapp.data.local

import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.data.local.room.ProfileDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProfileLocalDataSource(
    private val profileDao: ProfileDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getProfile() = profileDao.getProfile()

    fun insertProfile(profile: ProfileEntity) = CoroutineScope(ioDispatcher).launch {
        profileDao.insertProfile(profile)
    }

    fun deleteProfile() = CoroutineScope(ioDispatcher).launch {
        profileDao.deleteProfile()
    }
}