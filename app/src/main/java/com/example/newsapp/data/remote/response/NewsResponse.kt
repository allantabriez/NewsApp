package com.example.newsapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class NewsResponse(

    @SerialName("nsfw")
    val nsfw: Boolean? = null,

    @SerialName("channel")
    val channel: Channel? = null,

    @SerialName("created_at")
    val createdAt: String? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("cover_image")
    val coverImage: String? = null,

    @SerialName("counter")
    val counter: Counter? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Counter(

    @SerialName("view")
    val view: Int? = null,

    @SerialName("downvote")
    val downVote: Int? = null,

    @SerialName("comment")
    val comment: Int? = null,

    @SerialName("upvote")
    val upVote: Int? = null
)

@Serializable
data class Channel(

    @SerialName("name")
    val name: String? = null,

    @SerialName("id")
    val id: Int? = null
)
