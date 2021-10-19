package com.example.redditproject2.homepage

import android.graphics.Paint
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
            postID, postSelf ->  MaterialAlertDialogBuilder(requireContext())
            .setMessage("$postSelf")
            .setPositiveButton("Ok"){dialog,which ->

            }
            .show()
        /*Toast.makeText(context, "$postSelf", Toast.LENGTH_LONG).show()*/
        })
        return binding.root
        //return inflater.inflate(R.layout.home_fragment, container, false)
    }

}