package com.example.newsapp.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.local.entity.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert(entity = ProfileEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: ProfileEntity)

    @Query("DELETE FROM profile_table")
    fun deleteProfile()

    @Query("SELECT * FROM profile_table")
    fun getProfile(): Flow<List<ProfileEntity>>
}