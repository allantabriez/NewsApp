package com.example.newsapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewsResponse(

    val nsfw: Boolean? = null,

    val channel: Channel? = null,

    @SerialName("created_at")
    val createdAt: String? = null,

    val id: Int? = null,

    @SerialName("cover_image")
    val coverImage: String? = null,

    val counter: Counter? = null,

    val title: String? = null,

    val url: String? = null
)

@Serializable
data class Counter(
    val view: Int? = null,

    @SerialName("downvote")
    val downVote: Int? = null,

    val comment: Int? = null,

    @SerialName("upvote")
    val upVote: Int? = null
)

@Serializable
data class Channel(
    val name: String? = null,
    val id: Int? = null
)
