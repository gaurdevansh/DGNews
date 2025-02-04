package com.example.dgnews.data.repository

import androidx.datastore.preferences.protobuf.Api
import com.example.dgnews.data.api.NewsApi
import com.example.dgnews.data.api.RetrofitClient
import com.example.dgnews.data.models.ApiResponse
import com.example.dgnews.data.models.NewsResponse
import com.example.dgnews.utils.Constants

class NewsRepository {

    private val newsApi: NewsApi = RetrofitClient.retrofit.create(NewsApi::class.java)

    suspend fun getTopHeadlines(country: String = "us") =
        newsApi.getTopHeadlines(country, Constants.API_KEY)

    /*suspend fun getTopHeadlines2(country: String = "us"): ApiResponse<NewsResponse> {
        return try {
            val response = newsApi.getTopHeadlines(country, Constants.API_KEY)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    ApiResponse.Success(body)
                } else {
                    ApiResponse.Error("Empty Response", response.code())
                }
            } else {
                ApiResponse.Error("Error: ${response.message()}", response.code())
            }
        } catch (e: Exception) {
            ApiResponse.Error("Exception: ${e.localizedMessage}", 0)
        }
    }*/

    suspend fun getTopHeadlines3(country: String = "us"): ApiResponse<NewsResponse> {
        return try {
            val response = newsApi.getTopHeadlines(country, Constants.API_KEY)
            ApiResponse.Success(data = response.body()!!)
        }catch (e: Exception) {
            ApiResponse.Error(exception = e)
        }
    }
}