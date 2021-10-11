package com.example.newsapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(

	@SerialName("web")
	val web: String? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("bio")
	val bio: String? = null,

	@SerialName("picture")
	val picture: String? = null,

	@SerialName("username")
	val userName: String? = null
)