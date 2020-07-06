package com.juyao.jmvvmdemo.bean

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
@TypeConverters(StringTypeConverter::class)
data class Article(
    @PrimaryKey
    val _id: String,
    val author: String,
    val category: String,
    val createdAt: String,
    val desc: String,

    val images: List<String>,
    val likeCounts: Int,
    val publishedAt: String,
    val stars: Int,
    val title: String,
    val type: String,
    val url: String,
    val views: Int
)