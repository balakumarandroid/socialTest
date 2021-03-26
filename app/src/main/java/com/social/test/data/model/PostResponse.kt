package com.social.test.data.model

data class PostResponse(
	val postResponse: List<PostResponseItem?>? = null
)

data class PostResponseItem(
	val id: Int? = null,
	val title: String? = null,
	val body: String? = null,
	val userId: Int? = null,
	var userImage: String? = null
)

