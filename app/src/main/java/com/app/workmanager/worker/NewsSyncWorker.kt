package com.app.workmanager.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.app.workmanager.domain.repository.NewsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * A background worker responsible for synchronizing news articles from the remote source
 * to the local database.
 *
 * This worker uses [NewsRepository] to perform the sync operation. It is managed by
 * WorkManager and supports periodic or immediate execution.
 *
 * @param context The application context.
 * @param workerParams Parameters for the worker.
 * @param repository The repository used to perform news synchronization.
 */
@HiltWorker
class NewsSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: NewsRepository
) : CoroutineWorker(context, workerParams) {

    /**
     * Executes the background task of syncing news.
     *
     * @return [Result.success] if the sync was successful, [Result.retry] if the sync failed
     * but can be retried later, or [Result.failure] if an unexpected exception occurred.
     */
    override suspend fun doWork(): Result {
        return try {
            val syncResult = repository.syncNews()
            if (syncResult.isSuccess) {
                Result.success()
            } else {
                Result.retry()
            }
        } catch (_: Exception) {
            Result.failure()
        }
    }
}
