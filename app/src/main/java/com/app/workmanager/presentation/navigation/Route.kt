package com.app.workmanager.presentation.navigation

import kotlinx.serialization.Serializable

/**
 * Defines the navigation routes for the application.
 *
 * This sealed interface uses Kotlinx Serialization to provide type-safe navigation
 * with the Jetpack Compose Navigation library.
 */
@Serializable
sealed interface Route {
    /**
     * Route representing the main news list screen.
     */
    @Serializable
    data object NewsList : Route

    /**
     * Route representing the detailed view of a specific news article.
     *
     * @property title The headline of the article.
     * @property content The main text content of the article.
     * @property imageUrl The URL for the article's representative image.
     * @property sourceName The name of the news organization.
     * @property publishedAt The ISO-8601 formatted string of when the article was published.
     */
    @Serializable
    data class NewsDetail(
        val title: String,
        val content: String?,
        val imageUrl: String?,
        val sourceName: String,
        val publishedAt: String
    ) : Route
}
