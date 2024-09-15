package com.example.retrofitproject2.api

import com.example.retrofitproject2.pojo.NewsArticles
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getArticles(
        @Query("page")page:Int=1,
        @Query("country")country:String="us",
        @Query("apiKey")apiKey:String="da7f48fb63c744c1b020c6a1e7016f1c"
    ):retrofit2.Response<NewsArticles>
}