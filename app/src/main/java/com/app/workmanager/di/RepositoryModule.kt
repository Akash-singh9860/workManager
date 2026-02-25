package com.app.workmanager.di

import com.app.workmanager.data.repository.NewsRepositoryImpl
import com.app.workmanager.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module that provides repository implementations for the domain layer.
 *
 * This module is responsible for binding abstraction to their concrete implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds the [NewsRepositoryImpl] to its interface [NewsRepository].
     *
     * @param newsRepositoryImpl The concrete implementation of the repository.
     * @return The repository abstraction.
     */
    @Binds
    @Singleton
    abstract fun bindNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl
    ): NewsRepository
}
