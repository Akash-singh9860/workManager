package com.app.workmanager.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.work.WorkInfo
import com.app.workmanager.presentation.detail.DetailScreen
import com.app.workmanager.presentation.newsList.NewsListScreen
import com.app.workmanager.presentation.newsList.NewsViewModel

/**
 * The main navigation host for the application.
 *
 * It manages the navigation between the news list screen and the article detail screen.
 * It also handles the integration with Hilt for ViewModel injection and WorkManager for sync state.
 *
 * @param navController The [NavHostController] used to handle navigation.
 * @param modifier The [Modifier] to be applied to the [NavHost].
 */
@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.NewsList,
        modifier = modifier
    ) {
        composable<Route.NewsList> {
            val viewModel: NewsViewModel = hiltViewModel()
            val articles by viewModel.articles.collectAsStateWithLifecycle()
            val currentWorkInfoState by viewModel.syncState.collectAsStateWithLifecycle()

            NewsListScreen(
                articles = articles,
                isSyncing = currentWorkInfoState == WorkInfo.State.RUNNING,
                onArticleClick = { article ->
                    navController.navigate(
                        Route.NewsDetail(
                            title = article.title,
                            content = article.content ?: article.description,
                            imageUrl = article.imageUrl,
                            sourceName = article.sourceName,
                            publishedAt = article.publishedAt
                        )
                    )
                },
                onRefresh = { viewModel.refreshNews() }
            )
        }

        composable<Route.NewsDetail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Route.NewsDetail>()
            DetailScreen(
                title = detail.title,
                content = detail.content,
                imageUrl = detail.imageUrl,
                sourceName = detail.sourceName,
                publishedAt = detail.publishedAt,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
