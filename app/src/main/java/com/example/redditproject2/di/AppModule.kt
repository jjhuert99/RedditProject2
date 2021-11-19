package com.example.redditproject2.di

import com.example.redditproject2.network.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
        @Singleton
        @Provides
        fun provideCoroutines() = Dispatchers

        @Singleton
        @Provides
        fun provideRetrofitFactory() = RetrofitFactory
}
