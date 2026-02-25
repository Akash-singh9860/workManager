package com.app.workmanager.presentation.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.app.workmanager.domain.model.NewsArticle
import com.app.workmanager.domain.repository.NewsRepository
import com.app.workmanager.worker.WorkManagerScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel for the News List screen.
 *
 * It exposes the list of articles and the current synchronization state to the UI.
 * It also provides a method to manually trigger a refresh of the news data.
 *
 * @param repository The [NewsRepository] used to fetch articles.
 * @param scheduler The [WorkManagerScheduler] used to manage background tasks.
 * @param workManager The [WorkManager] instance used to observe task status.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    repository: NewsRepository,
    private val scheduler: WorkManagerScheduler,
    workManager: WorkManager
) : ViewModel() {

    /**
     * A [StateFlow] containing the list of [NewsArticle]s to be displayed.
     * It uses [SharingStarted.WhileSubscribed] to manage the resource lifecycle.
     */
    val articles: StateFlow<List<NewsArticle>> = repository.getNewsArticles()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * A [StateFlow] representing the current [WorkInfo.State] of the news sync worker.
     * This is used to show/hide loading indicators in the UI.
     */
    val syncState: StateFlow<WorkInfo.State?> = workManager
        .getWorkInfosForUniqueWorkFlow("NewsSyncWork")
        .map { it.firstOrNull()?.state }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    /**
     * Triggers an immediate background synchronization of news articles.
     */
    fun refreshNews() {
        scheduler.syncImmediately()
    }
}
