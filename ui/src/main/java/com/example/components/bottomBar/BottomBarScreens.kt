package com.example.components.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    object List: BottomBarScreens(
        route = "list",
        title = "Repositórios",
        icon = Icons.Rounded.AccountBox
    )

    object Favorite: BottomBarScreens(
        route = "bookmark",
        title = "Favoritos",
        icon = Icons.Rounded.Star
    )
}