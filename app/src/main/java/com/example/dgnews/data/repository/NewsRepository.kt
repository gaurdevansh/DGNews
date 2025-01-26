package com.example.dgnews.data.repository

import com.example.dgnews.data.api.NewsApi
import com.example.dgnews.data.api.RetrofitClient
import com.example.dgnews.utils.Constants

class NewsRepository {

    private val newsApi: NewsApi = RetrofitClient.retrofit.create(NewsApi::class.java)

    suspend fun getTopHeadlines(country: String = "us") =
        newsApi.getTopHeadlines(country, Constants.API_KEY)
}