package com.app.workmanager.data.repository

import com.app.workmanager.data.local.dao.NewsDao
import com.app.workmanager.data.mapper.toDomain
import com.app.workmanager.data.mapper.toEntity
import com.app.workmanager.data.remote.api.NewsApi
import com.app.workmanager.domain.model.NewsArticle
import com.app.workmanager.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of [NewsRepository] that coordinates data operations between
 * the remote [NewsApi] and local [NewsDao].
 *
 * This class acts as the single source of truth for news articles in the application.
 */
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao
) : NewsRepository {

    /**
     * Retrieves a stream of news articles from the local database.
     * The entities are mapped to domain models for use in the UI layer.
     *
     * @return A [Flow] emitting a list of [NewsArticle].
     */
    override fun getNewsArticles(): Flow<List<NewsArticle>> {
        return dao.getArticles().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    /**
     * Synchronizes the local database with the latest news from the remote API.
     *
     * It fetches articles from the network, clears the existing local data upon a successful
     * response, and persists the new articles.
     *
     * @return A [Result] indicating success or failure of the synchronization process.
     */
    override suspend fun syncNews(): Result<Unit> {
        return try {
            val response = api.getEverything()
            if (response.status == "ok") {
                val entities = response.articles.map { it.toEntity() }
                dao.clearAll()
                dao.insertArticles(entities)
                Result.success(Unit)
            } else {
                Result.failure(Exception("API Error: ${response.status}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
