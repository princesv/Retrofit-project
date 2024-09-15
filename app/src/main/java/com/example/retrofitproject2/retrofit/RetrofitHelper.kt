package com.example.retrofitproject2.retrofit

import com.example.retrofitproject2.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
        private var retrofit: Retrofit
        init {
            retrofit=Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        public fun getInstance(): Retrofit {
            return retrofit
        }
}