package com.example.redditproject2.network

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RedditNetworkImpl @Inject constructor(
    private val dispatcher: Dispatchers,
    private val retroObject: RedditEndPoints
    ) : RedditRepo{

    override suspend fun getPosts()
    : ServiceResult<RedditPost?> {
        return withContext(dispatcher.IO){
            val dataResponse = retroObject.getPosts()
            if(dataResponse.isSuccessful){
                ServiceResult.Succes(dataResponse.body())
            }else{
                ServiceResult.Error(Exception(dataResponse.errorBody().toString()))
            }
        }
    }
}
