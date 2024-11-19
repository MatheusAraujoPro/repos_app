package com.example.data_source

import com.example.model.Repo

interface RepoRemoteDataSource {
    suspend fun getRepos(): List<Repo>
    suspend fun getPagingRepos(pageSize: Int, page: Int): List<Repo>
}