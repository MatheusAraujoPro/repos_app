package com.example.github.screens.list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.components.card.RepoItem
import com.example.components.scaffold.DefaultScaffold
import com.example.components.scaffold.TopbarModel
import com.example.model.Repo
import com.example.utils.decideImageTec
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(viewModel: ListScreenViewModel = getViewModel()) {
    var isAppBarSearchVisibility by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    var listFilteredReposOnScreen by remember { mutableStateOf(listOf<Repo>()) }
    var isPagingReposFiltered by remember { mutableStateOf(false) }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(com.example.ui.R.raw.confete)
    )
    var isAnimationVisibile by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val pagingRepos = viewModel.pagingRepos.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    fun handleBarVisibility() {
        isAppBarSearchVisibility = !isAppBarSearchVisibility

        if (isAppBarSearchVisibility.not()) {
            isPagingReposFiltered = false
            listFilteredReposOnScreen = listOf()
        }
    }

    fun handleFilteredList(repoName: String) {
        listFilteredReposOnScreen = viewModel.getFilteredRepos(
            repoName = repoName,
            listRepo = pagingRepos.itemSnapshotList.items
        )
        isPagingReposFiltered = true
    }

    fun handleAniamtion() {
        isAnimationVisibile = isAnimationVisibile.not()
    }

    fun handleSnackBar() {
        scope.launch {
            delay(1_000)
            handleAniamtion()

            val result = snackbarHostState.showSnackbar(
                message = "Repositório favoritado!!",
                actionLabel = "ok",
                duration = SnackbarDuration.Long
            )

            when (result) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {}
            }
        }
    }

    LaunchedEffect(Unit, block = {
        viewModel.getPagingRepos()
        viewModel.getFavoriteReposIdList()
        viewModel.getCurrentRoute()
    })

    DefaultScaffold(
        content = {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                if (isAnimationVisibile)
                    LottieAnimation(
                        modifier = Modifier,
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                    )
                else
                    when (pagingRepos.loadState.refresh) {
                        LoadState.Loading -> {
                            CircularProgressIndicator()
                        }

                        is LoadState.Error -> {
                            //TODO: I could put a empty state
                        }

                        else -> {
                            if (isPagingReposFiltered) {
                                LazyColumn {
                                    items(listFilteredReposOnScreen) { item ->
                                        RepoItem(
                                            description = item.description.orEmpty(),
                                            language = item.language.orEmpty(),
                                            name = item.name,
                                            imageURl = decideImageTec(language = item.language.orEmpty()),
                                            onClick = {
                                                viewModel.saveLocalRepo(repo = item)
                                                handleAniamtion()
                                                handleSnackBar()
                                            },
                                            isRepoSaved = viewModel.favoriteReposIdList.contains(
                                                item.id
                                            ),
                                            route = viewModel.currentRoute.value
                                        )
                                    }
                                }

                            } else {
                                LazyColumn {
                                    itemsIndexed(pagingRepos) { _, item ->
                                        RepoItem(
                                            description = item?.description.orEmpty(),
                                            language = item?.language.orEmpty(),
                                            name = item?.name.orEmpty(),
                                            imageURl = decideImageTec(language = item?.language.orEmpty()),
                                            onClick = {
                                                viewModel.saveLocalRepo(repo = item!!)
                                                handleAniamtion()
                                                handleSnackBar()
                                            },
                                            isRepoSaved = viewModel.favoriteReposIdList.contains(
                                                item?.id
                                            ),
                                            route = viewModel.currentRoute.value
                                        )
                                    }
                                }
                            }
                        }
                    }
            }
        },
        topBar = TopbarModel(
            isSearch = isAppBarSearchVisibility,
            handleSearchRepo = {
                text = it
                handleFilteredList(repoName = text)
            },
            handleBarVisibility = { handleBarVisibility() },
            route = viewModel.currentRoute.value,
            text = text
        ),

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        navigate = { route ->
            viewModel.navigate(route = route)
        }
    )
}

