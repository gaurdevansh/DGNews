package com.example.dgnews.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dgnews.data.models.ApiResponse
import com.example.dgnews.data.models.Article
import com.example.dgnews.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    private val repository = NewsRepository()

    private val _article = MutableLiveData<Article>()
    val article : LiveData<Article> = _article

    fun fetchArticleByUrl(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getTopHeadlines3()
            if (response is ApiResponse.Success) {
               _article.postValue(response.data.articles.firstOrNull { it.url == url })
            }
        }
    }
}