package com.example.retrofitproject2.room

import androidx.lifecycle.ViewModel
import com.example.retrofitproject2.pojo.Article

class ArticlesViewModel: ViewModel() {
    var articleList= listOf<Article>()
    fun getNewsArticleList():List<Article>{
        return articleList
    }
    fun setNewsArticleList(mList: List<Article>){
        if(mList!=null) {
            articleList=mList
        }
    }

}