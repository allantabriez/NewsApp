package com.example.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginErrorResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("fields")
	val fields: List<FieldsItem?>? = null
)

data class FieldsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("error")
	val error: String? = null
)
