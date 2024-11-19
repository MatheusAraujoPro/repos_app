package com.example.data_source

import com.example.model.Repo

interface RepoLocalDataSource {
    suspend fun getRepos(): List<Repo>
    suspend fun saveRepo(repo: Repo)
    suspend fun getStoredRepoIds(): List<Int>
    suspend fun deleteLocalRepo(repo: Repo)
}