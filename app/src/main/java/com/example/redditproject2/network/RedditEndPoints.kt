package com.example.redditproject2.network

import retrofit2.Response
import retrofit2.http.GET

interface RedditEndPoints {
    @GET("androiddev.json")
    suspend fun getPosts():
            Response<RedditPost>
}
