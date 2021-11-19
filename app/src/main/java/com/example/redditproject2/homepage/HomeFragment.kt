package com.example.redditproject2.homepage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.redditproject2.R
import com.example.redditproject2.databinding.HomeFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.postPosts.adapter = RedditPostAdapter(Clicked {
            postID, postSelf, webLink, webFalse ->
            if(!webFalse){
                    val base_url = "https://www.reddit.com"
                    val sb = StringBuilder()
                    sb.append(base_url).append(webLink)
                    val completeURL = sb.toString()
                gotoUrl(completeURL)
            }
            else {
                var postInfo: String = postSelf
                if(postSelf.length < 1){
                    postInfo = "Sorry, Post has no description."
                }
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(postInfo)
                    .setPositiveButton("Ok") { dialog, which ->
                    }
                    .show()
            }
        })
        return binding.root
    }

    private fun gotoUrl(destURL: String) {
        val webIntent: Intent = Uri.parse(destURL).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(webIntent)
    }
}
