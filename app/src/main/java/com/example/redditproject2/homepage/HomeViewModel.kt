package com.example.redditproject2.homepage

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redditproject2.network.Children
import com.example.redditproject2.network.RedditRepo
import com.example.redditproject2.network.ServiceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val RedditRepo: RedditRepo,
    private val dispatcher: Dispatchers
): ViewModel() {
    enum class RedditStatus {ERROR, DONE}

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val _status = MutableLiveData<RedditStatus>()
    val status: LiveData<RedditStatus> = _status

    private val _post = MutableLiveData<List<Children>>()
    val post: LiveData<List<Children>>
        get() = _post

    init {
        getRedditPosts()
    }

    private fun getRedditPosts() {
        viewModelScope.launch(dispatcher.IO) {
            when(val response = RedditRepo.getPosts()){
                is ServiceResult.Succes ->{
                    _post.postValue(response.data?.data?.children)
                    _status.postValue(RedditStatus.DONE)
                }
                is ServiceResult.Error ->{
                    _status.postValue(RedditStatus.ERROR)
                }
                else ->{
                    _status.postValue(RedditStatus.ERROR)
                }
            }
        }
    }
}
