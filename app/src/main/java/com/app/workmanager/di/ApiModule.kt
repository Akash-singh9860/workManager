package com.app.workmanager.di

import com.app.workmanager.data.remote.api.NewsApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger module that provides network-related dependencies.
 *
 * This module is responsible for configuring and providing instances of [NewsApi],
 * [OkHttpClient], [Retrofit], and [Json] serialization.
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    /**
     * Provides an [HttpLoggingInterceptor] configured to log the body of HTTP requests and responses.
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /**
     * Provides a singleton instance of [OkHttpClient] with a logging interceptor.
     *
     * @param loggingInterceptor The interceptor used for logging network traffic.
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /**
     * Provides a singleton instance of [Json] configured for Kotlinx Serialization.
     *
     * It is configured to ignore unknown keys in the JSON response, which is common in NewsAPI.
     */
    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true // Crucial for NewsAPI as it returns many unused fields
            coerceInputValues = true
        }
    }

    /**
     * Provides a singleton implementation of [NewsApi] using Retrofit.
     *
     * @param okHttpClient The client used to make network requests.
     * @param json The JSON serializer/deserializer.
     */
    @Provides
    @Singleton
    fun provideNewsApi(okHttpClient: OkHttpClient, json: Json): NewsApi {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(NewsApi::class.java)
    }
}
