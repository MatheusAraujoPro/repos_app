package com.example.github.screens.list_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.github.navigation.definition.GithubNavigation
import com.example.model.Repo
import com.example.use_case.GetFilteredRepos
import com.example.use_case.GetLocalFavoritedReposUseCase
import com.example.use_case.GetPagingRepos
import com.example.use_case.GetRepos
import com.example.use_case.SaveLocalReposUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ListScreenViewModel(
    private val getRepoUseCase: GetRepos,
    private val getFilteredReposUseCase: GetFilteredRepos,
    private val getPagingReposUseCase: GetPagingRepos,
    private val saveLocalReposUseCase: SaveLocalReposUseCase,
    private val getLocalFavoritedReposUseCase: GetLocalFavoritedReposUseCase
) : ViewModel(), KoinComponent {

    private val _repos = mutableStateListOf<Repo>()
    private val _pagingRepos = mutableStateOf<Flow<PagingData<Repo>>>(emptyFlow())
    private val _favoriteReposIdList = mutableStateOf(listOf<Int>())
    private val _currentRoute = mutableStateOf("")
    private val navigation: GithubNavigation by inject()
    val pagingRepos: Flow<PagingData<Repo>> get() = _pagingRepos.value
    val favoriteReposIdList: MutableList<Int> get() = _favoriteReposIdList.value.toMutableList()
    val currentRoute: MutableState<String> = _currentRoute

    fun getPagingRepos() {
        _pagingRepos.value = Pager(PagingConfig(pageSize = 6)) {
            getPagingReposUseCase
        }.flow
    }

    fun getFilteredRepos(repoName: String, listRepo: List<Repo>): List<Repo> {
        return getFilteredReposUseCase.filterRepos(repoName, listRepo)
    }

    fun navigate(route: String) {
        navigation.navigate(route = route)
    }

    fun saveLocalRepo(repo: Repo) {
        viewModelScope.launch {
            saveLocalReposUseCase(
                repo = repo
            )
        }
    }

    fun getFavoriteReposIdList() {
        viewModelScope.launch {
            _favoriteReposIdList.value =
                getLocalFavoritedReposUseCase().toMutableList()
        }
    }

    fun getCurrentRoute() {
        _currentRoute.value = navigation.getCurrentDestination()
    }
}