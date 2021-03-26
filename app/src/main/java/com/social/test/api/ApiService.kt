package com.social.test.api

import com.social.test.data.model.PhotosResponseItem
import com.social.test.data.model.PostResponseItem
import com.social.test.data.model.UserResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    fun getUsers(): Call<List<UserResponseItem>>

    @GET("/photos")
    fun getPhotoList(): Call<List<PhotosResponseItem>>

    @GET("/posts?")
    fun getPostList( @Query("userId") userId: String?):Call<List<PostResponseItem>>
}