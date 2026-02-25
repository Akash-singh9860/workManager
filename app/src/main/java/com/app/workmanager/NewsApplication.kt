package com.app.workmanager

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * The base [Application] class for the app.
 *
 * Annotated with [HiltAndroidApp] to trigger Hilt's code generation.
 * It also implements [Configuration.Provider] to provide a custom configuration for WorkManager,
 * enabling dependency injection within Workers.
 */
@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider {

    /**
     * Injected [HiltWorkerFactory] that allows WorkManager to instantiate Workers
     * using Hilt dependency injection.
     */
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    /**
     * Provides the custom [Configuration] for WorkManager, integrating the [HiltWorkerFactory].
     */
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
