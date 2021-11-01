package com.example.newsapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.domain.model.News

@Entity(tableName = "news_table")
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "cover_image")
    val coverImg: String,

    @ColumnInfo(name = "nsfw")
    val nsfw: Boolean,

    @ColumnInfo(name = "created_at")
    val createdAt: String,

    @ColumnInfo(name = "channel_id")
    val channelId: Int,

    @ColumnInfo(name = "channel_name")
    val channelName: String,

    @ColumnInfo(name = "upvote")
    val upVotes: Int,

    @ColumnInfo(name = "downvote")
    val downVotes: Int,

    @ColumnInfo(name = "comment")
    val comments: Int,

    @ColumnInfo(name = "view")
    val views: Int
)

fun NewsEntity.toModel() = News(
    id = this.id,
    title = this.title,
    coverImg = this.coverImg,
    date = this.createdAt,
    channelId = this.channelId,
    channelName = this.channelName,
    upVotes = this.upVotes,
    downVotes = this.downVotes,
    comments = this.comments,
    views = this.views
)