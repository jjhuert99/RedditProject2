package com.example.redditproject2

import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.redditproject2.homepage.RedditPostAdapter
import com.example.redditproject2.network.Children

@BindingAdapter("listPosts")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Children>?){
    val adapter = recyclerView.adapter as RedditPostAdapter
    adapter.submitList(data)
}

@BindingAdapter("underlinedTitle")
fun underlineTitle(textView: TextView, text: String){
    val string = SpannableString(text)
    string.setSpan(UnderlineSpan(), 0, text.length-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.text = string
}
