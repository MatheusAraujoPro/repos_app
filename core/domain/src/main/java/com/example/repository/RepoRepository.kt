package com.example.repository

import com.example.model.Repo


interface RepoRepository {
    suspend fun getRepos(): List<Repo>
    suspend fun getPagingRepos(pageSize: Int, page: Int): List<Repo>
    suspend fun getLocalRepos(): List<Repo>
    suspend fun saveLocalRepos(repo: Repo)
    suspend fun getStoredRepoIds(): List<Int>
    suspend fun deleteLocalRepo(repo: Repo)
}