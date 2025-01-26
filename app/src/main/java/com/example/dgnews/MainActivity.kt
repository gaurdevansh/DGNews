package com.example.dgnews

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dgnews.data.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val repository = NewsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getTopHeadlines()
                Log.d("Articles:", "Articles** = ${response.toString()}")
                if (response.isSuccessful) {
                    val articles = response.body()?.articles ?: emptyList()
                    withContext(Dispatchers.Main) {
                        for (article in articles) {
                            Log.d("Article:", "Article = ${article.toString()}")
                        }
                    }
                } else {
                    Log.e("Error:", "Failed to fetch articles: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("Exception: ", "Error occured: ${e.message}")
            }
        }
    }
}