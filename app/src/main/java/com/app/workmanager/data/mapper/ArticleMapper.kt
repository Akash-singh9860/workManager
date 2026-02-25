package com.app.workmanager.data.mapper

import com.app.workmanager.data.local.entity.NewsEntity
import com.app.workmanager.data.remote.dto.ArticleDto
import com.app.workmanager.domain.model.NewsArticle

/**
 * Maps an [ArticleDto] from the network response to a [NewsEntity] for local storage.
 *
 * @return A [NewsEntity] containing the article's data.
 */
fun ArticleDto.toEntity(): NewsEntity {
    return NewsEntity(
        url = url,
        title = title,
        description = description,
        author = author,
        imageUrl = urlToImage,
        sourceName = source.name,
        publishedAt = publishedAt,
        content = content
    )
}

/**
 * Maps a [NewsEntity] from the local database to a [NewsArticle] domain model.
 *
 * @return A [NewsArticle] instance to be used in the UI and domain layers.
 */
fun NewsEntity.toDomain(): NewsArticle {
    return NewsArticle(
        title = title,
        description = description,
        content = content,
        url = url,
        imageUrl = imageUrl,
        publishedAt = publishedAt,
        sourceName = sourceName
    )
}
