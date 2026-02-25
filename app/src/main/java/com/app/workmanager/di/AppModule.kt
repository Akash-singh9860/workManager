package com.app.workmanager.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module that provides application-level dependencies.
 *
 * This module is responsible for providing singleton instances of [Context] and [WorkManager]
 * to be used across the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides the application [Context].
     *
     * @param context The Hilt-provided application context.
     * @return The application context instance.
     */
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    /**
     * Provides the singleton instance of [WorkManager].
     *
     * @param context The application context required to initialize [WorkManager].
     * @return The [WorkManager] instance.
     */
    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }
}
