package com.example.newsapp.data.remote.response

import com.example.newsapp.domain.model.Profile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(

    @SerialName("web")
    val web: String,

    @SerialName("name")
    val name: String,

    @SerialName("bio")
    val bio: String,

    @SerialName("picture")
    val picture: String,

    @SerialName("username")
    val userName: String
)

fun ProfileResponse.toModel() = Profile(
    name = this.name,
    bio = this.bio,
    web = this.web,
    picture = this.picture
)
