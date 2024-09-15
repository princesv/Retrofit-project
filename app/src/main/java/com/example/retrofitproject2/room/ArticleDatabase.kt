package com.example.retrofitproject2.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.retrofitproject2.pojo.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getDao():ArticlesDAO
}