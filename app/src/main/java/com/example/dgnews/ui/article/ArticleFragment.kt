package com.example.dgnews.ui.article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.dgnews.R
import com.example.dgnews.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private lateinit var binding : FragmentArticleBinding
    private val articleViewModel by viewModels<ArticleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val articleUrl = arguments?.getString("url")
        articleUrl?.let {
            articleViewModel.fetchArticleByUrl(it)
        }
        setupClickListeners()
        observeData()
    }

    private fun setupClickListeners() {
        with(binding) {
            backBtn.setOnClickListener {
                findNavController().navigate(R.id.action_articleFragment_to_homeFragment)
            }
        }
    }

    private fun observeData() {
        articleViewModel.article.observe(viewLifecycleOwner) { article ->
            with(binding) {
                context?.let {
                    Glide.with(it)
                        .load(article.urlToImage)
                        .into(newsPoster)
                }
                newsHeading.text = article.title
                newsAuthor.text = "By ${article.author}" ?: "Anonymous"
                newsSource.text = "At ${article.source?.name}" ?: "Anonymous"
                newsTime.text = article.publishedAt?.subSequence(0,10) ?: "Anonymous"
                newsContent.text = article.description
                newsUrl.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    startActivity(intent)
                }
            }
        }
    }
}