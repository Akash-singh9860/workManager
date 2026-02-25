package com.app.workmanager.data.remote.api

import com.app.workmanager.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API interface for fetching news from NewsAPI.org.
 */
interface NewsApi {
    
    /**
     * Fetches news articles based on a search query.
     *
     * @param query The search query string (e.g., "india").
     * @param sortBy The order to sort articles in (e.g., "publishedAt").
     * @param apiKey The API key for authenticating with NewsAPI.org.
     * @return A [NewsResponseDto] containing the list of articles and response status.
     */
    @GET("v2/everything")
    suspend fun getEverything(
        @Query("q") query: String = "india",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "c5d873853954403aa5d191c7bfb10058"
    ): NewsResponseDto

    companion object {
        /**
         * The base URL for the NewsAPI service.
         */
        const val BASE_URL = "https://newsapi.org/"
    }
}
