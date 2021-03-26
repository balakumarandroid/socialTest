package com.social.test.data.model

data class PhotosResponse(
	val photosResponse: List<PhotosResponseItem?>? = null
)

data class PhotosResponseItem(
	val albumId: Int? = null,
	val id: Int? = null,
	val title: String? = null,
	val url: String? = null,
	val thumbnailUrl: String? = null
)

