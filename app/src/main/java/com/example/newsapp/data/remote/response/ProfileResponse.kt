package com.example.newsapp.data.remote.response

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