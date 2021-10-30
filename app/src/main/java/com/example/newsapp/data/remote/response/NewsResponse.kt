package com.example.newsapp.data.remote.response

import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.domain.model.News
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
): ResponseMapper<News, NewsEntity> {
    override fun toEntity(): NewsEntity {
        return NewsEntity(
            id = this.id,
            title = this.title,
            coverImg = this.coverImage,
            createdAt = this.createdAt,
            channelId = this.channel.id,
            channelName = this.channel.name,
            upVotes = this.counter.upVote,
            downVotes = this.counter.downVote,
            comments = this.counter.comment,
            views = this.counter.view,
            nsfw = this.nsfw,
            url = this.url
        )
    }

    override fun toModel(): News {
        return News(
            id = this.id,
            title = this.title,
            coverImg = this.coverImage,
            date = this.createdAt,
            channelId = this.channel.id,
            channelName = this.channel.name,
            upVotes = this.counter.upVote,
            downVotes = this.counter.downVote,
            comments = this.counter.comment,
            views = this.counter.view
        )
    }
}

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

