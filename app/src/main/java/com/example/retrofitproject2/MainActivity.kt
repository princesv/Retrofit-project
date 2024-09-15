package com.example.retrofitproject2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.retrofitproject2.adapter.NewsAdapter
import com.example.retrofitproject2.api.NewsApi
import com.example.retrofitproject2.pojo.NewsArticles
import com.example.retrofitproject2.retrofit.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    lateinit var retrofit: Retrofit
    lateinit var rv: RecyclerView
    lateinit var rvAdapter:NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv=findViewById(R.id.rv_news)
        retrofit=RetrofitHelper.getInstance()
        rv.layoutManager= LinearLayoutManager(this)
        val newsApi=retrofit.create(NewsApi::class.java)
        getNewsFromWebAndInflateRecyclerView(newsApi)
    }
    fun getNewsFromWebAndInflateRecyclerView(newsApi:NewsApi){
        val crGetDataFromWeb=CoroutineScope(Dispatchers.IO).async {
            newsApi.getArticles()
        }
        CoroutineScope(Dispatchers.Main).launch {
            rv.adapter= crGetDataFromWeb.await().body()?.let {
                val adapter=NewsAdapter(it)
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