package com.example.retrofitproject2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofitproject2.api.NewsApi
import com.example.retrofitproject2.retrofit.RetrofitHelper
import com.example.retrofitproject2.ui.theme.RetrofitProject2Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofit=RetrofitHelper.getInstance()
        val newsApi=retrofit.create(NewsApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val res=newsApi.getArticles()
            Log.d("Network response","Total articles: ${res.body()?.totalResults}")
        }
    }
}