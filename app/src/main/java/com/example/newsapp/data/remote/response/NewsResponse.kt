package com.example.newsapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("nsfw")
	val nsfw: Boolean? = null,

	@field:SerializedName("channel")
	val channel: Channel? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("cover_image")
	val coverImage: String? = null,

	@field:SerializedName("counter")
	val counter: Counter? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Counter(

	@field:SerializedName("view")
	val view: Int? = null,

	@field:SerializedName("downvote")
	val downvote: Int? = null,

	@field:SerializedName("comment")
	val comment: Int? = null,

	@field:SerializedName("upvote")
	val upvote: Int? = null
)

data class Channel(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
