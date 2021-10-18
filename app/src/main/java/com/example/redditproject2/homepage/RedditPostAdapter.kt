package com.example.redditproject2.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.redditproject2.databinding.PostViewHolderBinding
import com.example.redditproject2.network.Children

class RedditPostAdapter : ListAdapter<Children, RedditPostAdapter.PostViewHolder>(DiffCallback){
    class PostViewHolder(private var binding: PostViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(child: Children){
                binding.post = child
                binding.executePendingBindings()
            }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Children>() {
        override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
            return oldItem.data.id == newItem.data.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RedditPostAdapter.PostViewHolder {
        return PostViewHolder(PostViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: RedditPostAdapter.PostViewHolder, position: Int) {
        val redditPost = getItem(position)
        holder.bind(redditPost)
    }
}