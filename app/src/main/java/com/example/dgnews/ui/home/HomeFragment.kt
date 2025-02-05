package com.example.dgnews.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dgnews.R
import com.example.dgnews.databinding.FragmentHomeBinding
import com.example.dgnews.ui.adapter.NewsAdapter


class HomeFragment : Fragment() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.fetchTopNews()
        initAdapter()
        /*homeViewModel.topNews.observe(viewLifecycleOwner) { articles ->
            Log.d("Articles", "Articles: $articles")
        }*/
        homeViewModel.topNewsResponse.observe(viewLifecycleOwner) { newsResponse ->
            //Log.d("Articles", newsResponse.articles.toString())
            newsAdapter.updateArticleList(newsResponse.articles)
        }
        homeViewModel.errorMsg.observe(viewLifecycleOwner) { error ->
            Log.d("Error", error)
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter { article ->
            val bundle = Bundle().apply {
                putString("url", article.url)
            }
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment, bundle)
        }
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.topNewsRv.layoutManager = layoutManager
        binding.topNewsRv.adapter = newsAdapter
    }
}