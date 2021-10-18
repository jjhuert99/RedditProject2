package com.example.redditproject2.homepage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redditproject2.network.RedditApi
import com.example.redditproject2.network.RedditPost
import com.example.redditproject2.network.Children

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getRedditPosts()
    }

    private fun getRedditPosts() {
        RedditApi.retrofitService.getPosts().enqueue(object: Callback<RedditPost> {
            override fun onFailure(call: Call<RedditPost>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<RedditPost>, response: Response<RedditPost>) {
                _response.value = "Success ${response.body()} Reddit Posts"
                Log.d("JJJ", "${response.body()?.data!!.children.size}")
            }
        })
        //_response.value = "set mars api here"
    }
}