package com.example.components.scaffold

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.components.bottomBar.BottomBar
import com.example.components.topBar.TopAppBarDefault

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultScaffold(
    content: @Composable () -> Unit,
    topBar: TopbarModel,
    snackbarHost: @Composable () -> Unit?,
    navigate: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBarDefault(
                text = topBar.text.orEmpty(),
                isSearch = topBar.isSearch,
                handleSearchRepo = topBar.handleSearchRepo,
                handleBarVisibility = topBar.handleBarVisibility,
                route = topBar.route
            )
        },
        bottomBar = {
            BottomBar(navigate = { route ->
                navigate.invoke(route)
            })
        },
        snackbarHost = { snackbarHost() }
    ) {
        content()
    }
}