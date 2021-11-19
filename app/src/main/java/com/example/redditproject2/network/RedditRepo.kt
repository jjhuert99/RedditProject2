package com.example.redditproject2.network

import kotlinx.coroutines.Dispatchers

interface RedditRepo {
    suspend fun getPosts(): ServiceResult<RedditPost?>

    companion object{
        fun provideRedditRepo(dispatcher: Dispatchers, retroObject: RedditEndPoints): RedditRepo{
            return RedditNetworkImpl(dispatcher, retroObject)
        }
    }
}
