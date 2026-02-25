package com.app.workmanager.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.workmanager.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the news_articles table.
 * Provides methods for querying, inserting, and deleting news articles.
 */
@Dao
interface NewsDao {

    /**
     * Retrieves all news articles from the database, ordered by publication date in descending order.
     *
     * @return A [Flow] emitting a list of [NewsEntity] objects.
     */
    @Query("SELECT * FROM news_articles ORDER BY publishedAt DESC")
    fun getArticles(): Flow<List<NewsEntity>>

    /**
     * Inserts a list of news articles into the database.
     * If an article with the same primary key already exists, it will be replaced.
     *
     * @param articles The list of [NewsEntity] to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<NewsEntity>)

    /**
     * Deletes all news articles from the database.
     */
    @Query("DELETE FROM news_articles")
    suspend fun clearAll()
}
