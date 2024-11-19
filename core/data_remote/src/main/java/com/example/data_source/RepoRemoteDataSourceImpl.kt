package com.example.data_source

import com.example.model.Repo
import com.example.response.getRepo
import com.example.services.ReposWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RepoRemoteDataSourceImpl(
    private val reposWebService: ReposWebService
) : RepoRemoteDataSource {
    override suspend fun getRepos(): List<Repo> {
        return withContext(Dispatchers.IO) {
            reposWebService.getRepos().map { repo -> repo.getRepo() }
        }
    }

    override suspend fun getPagingRepos(pageSize: Int, page: Int): List<Repo> {
        return withContext(Dispatchers.IO) {
            reposWebService.getPagingRepos(pageSize = pageSize, page = page)
                .map { repo -> repo.getRepo() }
        }
    }
}
