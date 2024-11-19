package com.example.github.screens.bookmark_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.components.card.RepoItem
import com.example.components.scaffold.DefaultScaffold
import com.example.components.scaffold.TopbarModel
import com.example.utils.decideImageTec
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Bookmark(viewModel: BookmarkScreenViewModel = getViewModel()) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true, key2 = viewModel.repos, block = {
        viewModel.getLocalRepos()
        viewModel.getCurrentRoute()
    })

    fun handleSnackBar() {
        scope.launch {
            delay(1_000)

            val result = snackbarHostState.showSnackbar(
                message = "Repositório deletado!!",
                actionLabel = "ok",
                duration = SnackbarDuration.Short
            )

            isLoading = when (result) {
                SnackbarResult.Dismissed -> {
                    false
                }

                SnackbarResult.ActionPerformed -> {
                    false
                }
            }
        }
    }

    DefaultScaffold(
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading)
                    CircularProgressIndicator()
                else
                    if (viewModel.repos.size > 0)
                        LazyColumn {
                            items(viewModel.repos) { item ->
                                RepoItem(
                                    description = item.description.orEmpty(),
                                    language = item.language.orEmpty(),
                                    name = item.name,
                                    imageURl = decideImageTec(language = item.language.orEmpty()),
                                    onClick = {
                                        viewModel.deleteRepo(repo = item)
                                        handleSnackBar()
                                        isLoading = true
                                    },
                                    isRepoSaved = true,
                                    route = viewModel.currentRoute.value
                                )
                            }
                        }
            }
        },
        topBar = TopbarModel(
            isSearch = false,
            handleSearchRepo = {},
            handleBarVisibility = { },
            route = viewModel.currentRoute.value,
            text = ""
        ),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        navigate = { route ->
            viewModel.navigate(route = route)
        }
    )
}