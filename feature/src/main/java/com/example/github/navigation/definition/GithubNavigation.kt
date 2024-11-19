package com.example.github.navigation.definition

interface GithubNavigation {
    fun navigate(route: String)
    fun getCurrentDestination(): String
}