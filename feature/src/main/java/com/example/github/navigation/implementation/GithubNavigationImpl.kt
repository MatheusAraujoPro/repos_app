package com.example.github.navigation.implementation

import com.example.github.navigation.definition.GithubNavigation
import com.example.utils.NavigationManager

class GithubNavigationImpl(
    private val navigationManager: NavigationManager
): GithubNavigation {
    override fun navigate(route: String) {
        navigationManager.navigate(
            route = route
        )
    }

    override fun getCurrentDestination(): String {
        return navigationManager.currentRoute.orEmpty()
    }
}