package com.example.redditproject2.homepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditproject2.network.RedditApi
import com.example.redditproject2.network.RedditPost
import com.example.redditproject2.network.Children
import com.example.redditproject2.network.DataNested
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _post = MutableLiveData<DataNested>()
    val post: LiveData<DataNested>
        get() = _post

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    init {
        getRedditPosts()
    }

    private fun getRedditPosts() {
        //RedditApi.retrofitService.getPosts().enqueue(object: Callback<RedditPost> {
            /*coroutineScope.launch {
                var getPostsDeferred = RedditApi.retrofitService.getPosts()
                var result = getPostsDeferred.await()
                override fun onResponse(call: Call<RedditPost>, response: Response<RedditPost>) {
                    _response.value =
                        "Success ${response.body()?.data!!.children.size} Reddit Posts"
                }

                override fun onFailure(call: Call<RedditPost>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }
            )
        }*/
        coroutineScope.launch {
            var getPostsDeferred = RedditApi.retrofitService.getPosts()
            try {
                var result = getPostsDeferred.await()
                _post.value = result.data.children[0].data
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}