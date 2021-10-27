package com.example.newsapp.domain.model

data class News(
    val id: Int,
    val title: String,
    val coverImg: String,
    val date: String,
    val channelId: Int,
    val channelName: String,
    val upVotes: Int,
    val downVotes: Int,
    val comments: Int,
    val views: Int
)
