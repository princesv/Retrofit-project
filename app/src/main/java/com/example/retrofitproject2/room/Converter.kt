package com.example.retrofitproject2.room

import androidx.room.TypeConverter
import com.example.retrofitproject2.pojo.Source

class Converter {
    @TypeConverter
    fun fromSource(source: Source):String?{
        if(source==null){
            return null
        }else{
            return source.id
        }
    }
    @TypeConverter
    fun toSource(string: String?=null):Source?{
        if(string==null){
            return null
        }
        return Source(string,string)
    }
}