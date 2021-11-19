package com.example.redditproject2.di

import com.example.redditproject2.network.RedditEndPoints
import com.example.redditproject2.network.RedditNetworkImpl
import com.example.redditproject2.network.RedditRepo
import com.example.redditproject2.network.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RedditNetworkModule {
    private const val BASE_URL = "https://www.reddit.com/r/"

    @Singleton
    @Provides
    fun provideRedditRetrofit(): RedditEndPoints{
        return RetrofitFactory.retrofitProvider(
            RedditEndPoints::class.java,
            BASE_URL
        )
    }

    @Singleton
    @Provides
    fun provideRedditRepo(dispatcher: Dispatchers, retroObject: RedditEndPoints): RedditRepo{
        return RedditNetworkImpl(dispatcher, retroObject)
    }
}
