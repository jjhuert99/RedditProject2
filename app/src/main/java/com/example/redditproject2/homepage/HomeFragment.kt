package com.example.redditproject2.homepage

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.redditproject2.databinding.HomeFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.lang.StringBuilder
import java.net.URI

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy{
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = HomeFragmentBinding.inflate(inflater, null)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.postPosts.adapter = RedditPostAdapter(Clicked {
            postID, postSelf, webLink, webFalse ->
            if(webFalse == false){
                //Toast.makeText(context, "Clicked Web", Toast.LENGTH_LONG).show()
                    val a = "https://www.reddit.com"
                    val sb = StringBuilder()
                    sb.append(a).append(webLink)
                    val completeURL = sb.toString()
                gotoUrl(completeURL)
            }
            else {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage("$postSelf")
                    .setPositiveButton("Ok") { dialog, which ->

                    }
                    .show()
            }
        })
        return binding.root
    }

    private fun gotoUrl(destURL: String) {
        /*val myUri = Uri.parse(destURL)
        startActivity(Intent(Intent.ACTION_VIEW,myUri))*/
        val webIntent: Intent = Uri.parse(destURL).let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(webIntent)
    }

}