package com.example.redditproject2.homepage

import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.redditproject2.databinding.PostViewHolderBinding
import com.example.redditproject2.network.Children
import com.example.redditproject2.network.DataNested

class RedditPostAdapter(val clickListener:Clicked) : ListAdapter<Children, RedditPostAdapter.PostViewHolder>(DiffCallback){
    class PostViewHolder(private var binding: PostViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(child: Children, clickListener: Clicked){
                binding.post = child
                binding.click = clickListener
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
    ): PostViewHolder {
        return PostViewHolder(PostViewHolderBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val redditPost = getItem(position)
        holder.bind(getItem(position), clickListener)
        holder.bind(redditPost, clickListener)
    }
}
class Clicked(val clickListener: (postID: String, postSelf: String, webLink: String, webFalse: Boolean) -> Unit){
    fun onClick(post: Children, webFalse: Boolean) = clickListener(post.data.id, post.data.selftext, post.data.permalink, webFalse)
}
