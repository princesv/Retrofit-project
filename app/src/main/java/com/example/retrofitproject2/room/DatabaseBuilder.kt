package com.example.retrofitproject2.room

import android.content.Context
import androidx.room.Room
import com.example.retrofitproject2.pojo.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch

object DatabaseBuilder {
    private var dbInstance: ArticleDatabase?=null
    suspend fun getInstance(context: Context): ArticleDatabase {
        if(dbInstance!=null){
            return dbInstance!!
        }
            var job=CoroutineScope(Dispatchers.IO).launch {
                dbInstance=Room.databaseBuilder(context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db").build()
            }
        job.join()
        return dbInstance!!
    }
}