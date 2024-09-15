package com.example.retrofitproject2.pojo

data class NewsArticles(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)