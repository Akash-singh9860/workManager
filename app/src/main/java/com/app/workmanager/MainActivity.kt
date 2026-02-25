package com.app.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.app.workmanager.presentation.navigation.MainNavHost
import com.app.workmanager.presentation.ui.theme.WorkManagerTheme
import com.app.workmanager.worker.WorkManagerScheduler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * The main entry point of the application.
 *
 * This activity sets up the UI using Jetpack Compose and initializes the
 * background synchronization process using [WorkManagerScheduler].
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Injected instance of [WorkManagerScheduler] used to schedule background news synchronization.
     */
    @Inject
    lateinit var scheduler: WorkManagerScheduler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        scheduler.schedulePeriodicNewsSync()
        setContent {
            WorkManagerTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavHost(navController = navController)
                }
            }
        }
    }
}
