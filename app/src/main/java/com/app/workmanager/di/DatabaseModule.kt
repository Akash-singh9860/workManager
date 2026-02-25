package com.app.workmanager.di

import android.content.Context
import androidx.room.Room
import com.app.workmanager.data.local.NewsDatabase
import com.app.workmanager.data.local.dao.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module that provides database-related dependencies.
 *
 * This module is responsible for providing the singleton instances of [NewsDatabase]
 * and [NewsDao] using Room.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Provides a singleton instance of [NewsDatabase].
     *
     * @param context The application context used to build the database.
     * @return The initialized [NewsDatabase] instance.
     */
    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    /**
     * Provides the [NewsDao] to be used for news-related database operations.
     *
     * @param db The [NewsDatabase] instance.
     * @return The [NewsDao] provided by the database.
     */
    @Provides
    @Singleton
    fun provideNewsDao(db: NewsDatabase): NewsDao {
        return db.newsDao
    }
}
