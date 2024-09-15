package com.example.retrofitproject2.pojo

import androidx.room.Entity

@Entity
data class Source(
    val id: String,
    val name: String
)