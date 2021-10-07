package com.example.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginErrorResponse(
	val message: String? = null,
	val fields: List<FieldsItem?>? = null
)

@Serializable
data class FieldsItem(
	val name: String? = null,
	val error: String? = null
)