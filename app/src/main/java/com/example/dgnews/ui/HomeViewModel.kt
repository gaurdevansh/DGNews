package com.example.dgnews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dgnews.data.models.ApiResponse
import com.example.dgnews.data.models.Article
import com.example.dgnews.data.models.NewsResponse
import com.example.dgnews.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val newsRepository = NewsRepository()

    private val _topNews = MutableLiveData<List<Article>>()
    val topNews : LiveData<List<Article>> = _topNews

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg : LiveData<String> = _errorMsg

    private val _topNewsResponse = MutableLiveData<NewsResponse>()
    val topNewsResponse : LiveData<NewsResponse> = _topNewsResponse

    fun fetchTopNews() {
        viewModelScope.launch(Dispatchers.IO) {
                val response = newsRepository.getTopHeadlines3()
                if (response is ApiResponse.Success) {
                    _topNewsResponse.postValue(response.data)
                }
                if (response is ApiResponse.Error) {
                    _errorMsg.postValue("Could Not Fetch Data due to ${response.exception.localizedMessage}")
                }
        }
    }

}