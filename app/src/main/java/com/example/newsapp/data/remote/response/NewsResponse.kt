package com.example.newsapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewsResponse(

    @SerialName("nsfw")
    val nsfw: Boolean,

    @SerialName("channel")
    val channel: Channel,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("id")
    val id: Int,

    @SerialName("cover_image")
    val coverImage: String,

    @SerialName("counter")
    val counter: Counter,

    @SerialName("title")
    val title: String,

    @SerialName("url")
    val url: String
)

@Serializable
data class Counter(

    @SerialName("view")
    val view: Int,

    @SerialName("downvote")
    val downVote: Int,

    @SerialName("comment")
    val comment: Int,

    @SerialName("upvote")
    val upVote: Int
)

@Serializable
data class Channel(

    @SerialName("name")
    val name: String,

    @SerialName("id")
    val id: Int
)
