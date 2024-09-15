package com.example.retrofitproject2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.retrofitproject2.adapter.NewsAdapter
import com.example.retrofitproject2.api.NewsApi
import com.example.retrofitproject2.pojo.NewsArticles
import com.example.retrofitproject2.retrofit.RetrofitHelper
import com.example.retrofitproject2.room.ArticleDatabase
import com.example.retrofitproject2.room.ArticlesViewModel
import com.example.retrofitproject2.room.DatabaseBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    lateinit var retrofit: Retrofit
    lateinit var rv: RecyclerView
    lateinit var dbInstance: ArticleDatabase
    lateinit var articleViewModel: ArticlesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv=findViewById(R.id.rv_news)
        retrofit=RetrofitHelper.getInstance()
        rv.layoutManager= LinearLayoutManager(this)
        articleViewModel=ViewModelProvider(this).get(ArticlesViewModel::class.java)
        val newsApi=retrofit.create(NewsApi::class.java)
        GlobalScope.launch {
            getNewsFromWebAndInflateRecyclerView(newsApi)
        }
        GlobalScope.launch {
            delay(10000)
            val resp=dbInstance.getDao().getArticles()
            for(i in resp){
                Log.d("TABLE_DATA",": ${i.title}")
            }
        }
    }
    suspend fun getNewsFromWebAndInflateRecyclerView(newsApi:NewsApi){
        val crGetDataFromWeb=CoroutineScope(Dispatchers.IO).async {
            newsApi.getArticles()
        }

        CoroutineScope(Dispatchers.IO).launch{
            dbInstance=DatabaseBuilder.getInstance(applicationContext)
            crGetDataFromWeb.await().body()?.let { dbInstance.getDao().insertArticles(it.articles) }
        }

        CoroutineScope(Dispatchers.Main).launch {
            rv.adapter= crGetDataFromWeb.await().body()?.let {
                articleViewModel.setNewsArticleList(it.articles)
                val adapter=NewsAdapter(articleViewModel)
                adapter.callFromMainActivity(object :NewsAdapter.onNewsItemClickListener{
                    override fun onItemClickListener(position: Int) {
                        Log.d("ITEM CLICKED","Position: $position")
                    }

                })
                adapter
            }
        }
    }
    suspend fun inflateRecyclerView(newsArticles: NewsArticles){
    }

}