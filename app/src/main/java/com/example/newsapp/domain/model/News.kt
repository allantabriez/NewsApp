package com.example.newsapp.domain.model

data class News(
    val id: Int,
    val title: String? = "",
    val coverImg: String? = null,
    val date: String? = null,
    val channel: Channel? = null,
    val counter: Counter? = null
)

data class Channel(
    val id: Int,
    val name: String? = null
)

data class Counter(
    val upVotes: Int,
    val downVotes: Int,
    val comments: Int,
    val views: Int
)
