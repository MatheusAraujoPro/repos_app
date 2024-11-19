package com.example.response

import com.example.model.Repo


data class RepoResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val language: String?
)

fun RepoResponse.getRepo(): Repo {
    return Repo(
        id = id,
        name = name,
        description = description,
        language = language
    )
}
