package com.example.modulartemplate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.github.screens.list_screen.ListScreen
import com.example.github.screens.bookmark_screen.Bookmark

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "list",
    ) {
        composable("list"){ ListScreen() }
        composable("bookmark"){ Bookmark() }
    }
}