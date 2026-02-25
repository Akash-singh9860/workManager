package com.app.workmanager.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.workmanager.data.local.dao.NewsDao
import com.app.workmanager.data.local.entity.NewsEntity

/**
 * The Room database for the application.
 *
 * This class defines the database configuration and provides access to the DAOs.
 * It manages the [NewsEntity] table.
 */
@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    /**
     * Provides access to news article related database operations.
     */
    abstract val newsDao: NewsDao
}
