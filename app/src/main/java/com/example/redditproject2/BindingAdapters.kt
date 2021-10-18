package com.example.redditproject2

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.redditproject2.homepage.RedditPostAdapter
import com.example.redditproject2.network.Children

@BindingAdapter("listPosts")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Children>?){
    val adapter = recyclerView.adapter as RedditPostAdapter
    adapter.submitList(data)
}