package com.example.github.screens.bookmark_screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.navigation.definition.GithubNavigation
import com.example.model.Repo
import com.example.use_case.DeleteLocalRepoUseCase
import com.example.use_case.GetLocalReposUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookmarkScreenViewModel constructor(
    private val getLocalReposUseCase: GetLocalReposUseCase,
    private val deleteLocalRepoUseCase: DeleteLocalRepoUseCase
) : ViewModel(), KoinComponent {

    private val _repos = mutableStateListOf<Repo>()
    private val navigation: GithubNavigation by inject()
    private val _currentRoute = mutableStateOf("")


    val repos: MutableList<Repo> get() = _repos
    val currentRoute: MutableState<String> = _currentRoute

    fun navigate(route: String) {
        navigation.navigate(route = route)
    }

    fun getLocalRepos() {
        viewModelScope.launch {
            _repos.addAll(getLocalReposUseCase())
        }
    }

    fun deleteRepo(repo: Repo) {
        viewModelScope.launch {
            try {
                deleteLocalRepoUseCase(repo = repo)
                _repos.remove(element = repo)
            }catch (ex: Exception){
                Log.e("error", "Ocorreu um erro: $ex")
            }
        }
    }

    fun getCurrentRoute() {
        _currentRoute.value = navigation.getCurrentDestination()
    }
}