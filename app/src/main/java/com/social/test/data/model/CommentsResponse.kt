package com.social.test.data.model

data class CommentsResponse(
	val commentsResponse: List<CommentsResponseItem?>? = null
)

data class CommentsResponseItem(
	val name: String? = null,
	val postId: Int? = null,
	val id: Int? = null,
	val body: String? = null,
	val email: String? = null
)

