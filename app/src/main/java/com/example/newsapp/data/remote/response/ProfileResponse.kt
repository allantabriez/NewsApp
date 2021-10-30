package com.example.newsapp.data.remote.response

import com.example.newsapp.data.local.entity.ProfileEntity
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
): ResponseMapper<Profile, ProfileEntity> {
    override fun toEntity(): ProfileEntity {
        return ProfileEntity(
            name = this.name,
            bio = this.bio,
            web = this.web,
            picture = this.picture,
            userName = this.userName
        )
    }

    override fun toModel(): Profile {
        return Profile(
            name = this.name,
            bio = this.bio,
            web = this.web,
            picture = this.picture
        )
    }
}

