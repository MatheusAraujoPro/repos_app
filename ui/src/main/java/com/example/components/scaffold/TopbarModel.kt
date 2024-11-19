package com.example.components.scaffold

data class TopbarModel(
    val text: String?,
    val isSearch: Boolean,
    val handleSearchRepo: (String) -> Unit?,
    val handleBarVisibility: () -> Unit?,
    val route: String
)