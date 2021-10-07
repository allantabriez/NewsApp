package com.example.newsapp.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
	val web: String? = null,
	val name: String? = null,
	val bio: String? = null,
	val picture: String? = null,

	@SerialName("username")
	val userName: String? = null
)