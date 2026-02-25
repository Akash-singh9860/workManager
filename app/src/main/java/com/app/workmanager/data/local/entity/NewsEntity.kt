package com.app.workmanager.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_articles")
data class NewsEntity(
    @PrimaryKey val url: String,
    val title: String,
    val description: String?,
    val author: String?,
    val imageUrl: String?,
    val sourceName: String,
    val publishedAt: String,
    val content: String?
)