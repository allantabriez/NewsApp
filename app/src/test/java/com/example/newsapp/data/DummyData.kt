package com.example.newsapp.data

import com.example.newsapp.data.local.entity.NewsEntity
import com.example.newsapp.data.local.entity.ProfileEntity
import com.example.newsapp.domain.model.Token
import kotlin.math.exp

object DummyData {
    const val LOGIN_200 =
        "{\n  \"token\": \"somerandomtoken\",\n  \"scheme\": \"Bearer\",\n  \"expires_at\": \"2019-08-24T14:15:22Z\"\n}"
    const val BODY_401 = "{\n\"message\": \"error-message-from-server\"\n}"
    const val PROFILE_200 =
        "{\n\"username\": \"username\",\n\"name\": \"John Doe\",\n\"bio\": \"Lorem ipsum dolor si jamet\",\n\"web\": \"https://example.com\",\n\"picture\": \"https://img.example.com/profile.jpg\"\n}"
    const val NEWS_200 =
        "{\n\"data\": [\n{\n\"id\": 1,\n\"title\": \"Sample Title\",\n\"url\": \"https://example.com\",\n\"cover_image\": \"https://place-hold.it/600x400/aaa/000000.jpg&text=SampleImage&bold&fontsize=20\",\n\"nsfw\": true,\n\"channel\": {\n\"id\": 2,\n\"name\": \"r/sample\"\n},\n\"counter\": {\n\"upvote\": 2,\n\"downvote\": 3,\n\"comment\": 4,\n\"view\": 5\n},\n\"created_at\": \"2019-08-24T14:15:22Z\"\n}\n]\n}"
    val PROFILE_ENTITY = ProfileEntity(
        userName = "username",
        name = "John Doe",
        bio = "Lorem ipsum dolor si jamet",
        web = "https://example.com",
        picture = "https://img.example.com/profile.jpg"
    )
    val NEWS_ENTITY = NewsEntity(
        id = 1,
        title = "Sample Title",
        url = "https://example.com",
        coverImg = "https://place-hold.it/600x400/aaa/000000.jpg&text=SampleImage&bold&fontsize=20",
        nsfw = false,
        createdAt = "Someday",
        channelId = 1,
        channelName = "Sports",
        upVotes = 256,
        downVotes = 20,
        comments = 30,
        views = 500
    )
    val TOKEN = Token(
        expiresAt = "21 November 2021",
        token = "abdfefg12345"
    )
}