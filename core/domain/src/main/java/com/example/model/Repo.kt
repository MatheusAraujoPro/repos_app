package com.example.model

data class Repo(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?,
    val localID: Int? = null
)