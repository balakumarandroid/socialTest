package com.social.test.api

import com.social.test.data.model.UserResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/users")
    fun getUsers(): Call<List<UserResponseItem>>
}