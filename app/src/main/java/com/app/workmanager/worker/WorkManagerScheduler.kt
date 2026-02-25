package com.app.workmanager.worker

import android.content.Context
import androidx.work.*
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Helper class to schedule and manage background work using [WorkManager].
 *
 * This class provides methods to schedule periodic synchronization of news articles
 * and to trigger an immediate manual synchronization.
 */
@Singleton
class WorkManagerScheduler @Inject constructor(
    @field:ApplicationContext private val context: Context
) {
    private val workManager = WorkManager.getInstance(context)

    /**
     * Schedules a periodic background task to sync news articles.
     *
     * The task is configured with the following constraints:
     * - Requires an active network connection.
     * - Requires the device's battery not to be low.
     *
     * It runs periodically every 1 minute (for demonstration purposes) and uses a linear
     * backoff policy for retries. If the work is already scheduled, it keeps the existing one.
     */
    fun schedulePeriodicNewsSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<NewsSyncWorker>(
            repeatInterval = 1,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                WorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()
        workManager.enqueueUniquePeriodicWork(
            "NewsSyncWork",
            ExistingPeriodicWorkPolicy.KEEP,
            syncRequest
        )
    }

    /**
     * Triggers an immediate, one-time synchronization of news articles.
     *
     * This request is marked as expedited to run as soon as possible, subject to
     * system quotas.
     */
    fun syncImmediately() {
        val oneTimeRequest = OneTimeWorkRequestBuilder<NewsSyncWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
        workManager.enqueue(oneTimeRequest)
    }
}
