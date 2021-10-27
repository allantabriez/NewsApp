package com.example.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey
    @ColumnInfo(name = "userName")
    val userName: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "bio")
    val bio: String,
    @ColumnInfo(name = "web")
    val web: String,
    @ColumnInfo(name = "picture")
    val picture: String,
)