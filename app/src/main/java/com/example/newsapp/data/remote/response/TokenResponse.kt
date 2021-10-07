package com.example.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class TokenResponse(

	@field:SerializedName("expires_at")
	val expiresAt: String? = null,

	@field:SerializedName("scheme")
	val scheme: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
