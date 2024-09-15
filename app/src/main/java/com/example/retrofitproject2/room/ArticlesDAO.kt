package com.example.retrofitproject2.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofitproject2.pojo.Article
import retrofit2.http.DELETE
import retrofit2.http.GET

@Dao
interface ArticlesDAO {
    @Query("SELECT * FROM articles")
    fun getArticles():List<Article>

    @Delete
    fun deleteArticle(article:Article)

    @Insert
    fun insertArticles(articles:List<Article>)
}