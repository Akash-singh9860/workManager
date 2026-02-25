package com.app.workmanager.domain.repository

import com.app.workmanager.domain.model.NewsArticle
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing news data.
 *
 * This repository provides a stream of news articles and a way to synchronize
 * the local data with a remote source.
 */
interface NewsRepository {
    /**
     * Returns a stream of news articles.
     *
     * @return A [Flow] emitting a list of [NewsArticle]s.
     */
    fun getNewsArticles(): Flow<List<NewsArticle>>

    /**
     * Synchronizes the news articles from the remote source to the local storage.
     *
     * @return A [Result] indicating whether the synchronization was successful.
     */
    suspend fun syncNews(): Result<Unit>
}
