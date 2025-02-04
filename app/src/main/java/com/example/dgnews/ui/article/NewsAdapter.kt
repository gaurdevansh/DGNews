package com.example.dgnews.ui.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dgnews.data.models.Article
import com.example.dgnews.databinding.NewsHorizontalItemBinding

class NewsAdapter(
    private val clickListener: (Article) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val articlesList: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsHorizontalItemBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = articlesList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articlesList[position]
        holder.bind(article)
    }

    /**
     * Update the articles.
     */
    fun updateArticleList(articles: List<Article>) {
        articlesList.clear()
        articlesList.addAll(articles)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(private val binding: NewsHorizontalItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            with(binding) {
                newsTitle.text = article.title
                newsDescription.text = article.description
                newsTime.text = article.publishedAt
                Glide.with(root.context)
                    .load(article.urlToImage)
                    .into(newsPoster)
                root.setOnClickListener {
                    clickListener(article)
                }
            }
        }
    }
}