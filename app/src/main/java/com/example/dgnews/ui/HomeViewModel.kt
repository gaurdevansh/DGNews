package com.example.dgnews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dgnews.data.models.Article
import com.example.dgnews.data.repository.NewsRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val newsRepository = NewsRepository()

    private val _topNews = MutableLiveData<List<Article>>()
    val topNews : LiveData<List<Article>> = _topNews

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg : LiveData<String> = _errorMsg

    fun fetchTopNews() {
        viewModelScope.launch {
            try {
                val response = newsRepository.getTopHeadlines()
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    _topNews.postValue(articles)
                } else {
                    _errorMsg.postValue(response.message())
                }
            } catch (e: Exception) {
                _errorMsg.postValue(e.message)
            }
        }
    }

}