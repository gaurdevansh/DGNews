package com.example.dgnews.data.models

sealed class ApiResponse<T>(
    data: T? = null,
    exception: Exception? = null
) {
    data class Success<T>(val data: T): ApiResponse<T>(data, null)
    data class Error<T>(val exception: Exception): ApiResponse<T>(null, exception)
}