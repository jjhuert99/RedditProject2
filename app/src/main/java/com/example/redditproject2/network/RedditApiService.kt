package com.example.redditproject2.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.reddit.com/r/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RedditApiService{
    @GET("androiddev.json")
    fun getPosts():
            Call<String>
}

object RedditApi{
    val retrofitService: RedditApiService by lazy{
        retrofit.create(RedditApiService::class.java)
    }
}