package com.hado.githubrepositoriesinfinitescrolling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.hado.githubrepositoriesinfinitescrolling.ui.theme.GithubRepositoriesInfiniteScrollingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubRepositoriesInfiniteScrollingTheme {
                val viewModel: RepositoriesViewModel = viewModel()
                val reposFlow = viewModel.repositories
                val lazyRepoItems: LazyPagingItems<Repository> = reposFlow.collectAsLazyPagingItems()

                val timerText = viewModel.timerState.value

                RepositoriesScreen(
                    repos = lazyRepoItems,
                    timerText = timerText,
                    getTimer = { viewModel.timer },
                    onPauseTimer = { viewModel.timer.stop() }
                )
            }
        }
    }
}