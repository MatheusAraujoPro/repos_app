package com.example.repository


import com.example.data_source.RepoRemoteDataSource
import com.example.data_source.RepoLocalDataSource
import com.example.model.Repo

class RepoRepositoryImpl(
    private val remoteDataSource: RepoRemoteDataSource,
    private val localDataSource: RepoLocalDataSource
) : RepoRepository {
    override suspend fun getRepos(): List<Repo> {
        return remoteDataSource.getRepos()
    }

    override suspend fun getPagingRepos(pageSize: Int, page: Int): List<Repo> {
        return remoteDataSource.getPagingRepos(
            pageSize = pageSize,
            page = page
        )
    }

    override suspend fun getLocalRepos(): List<Repo> {
        return localDataSource.getRepos()
    }

    override suspend fun saveLocalRepos(repo: Repo){
        localDataSource.saveRepo(repo = repo)
    }

    override suspend fun getStoredRepoIds(): List<Int> {
        return localDataSource.getStoredRepoIds()
    }

    override suspend fun deleteLocalRepo(repo: Repo) {
        localDataSource.deleteLocalRepo(repo = repo)
    }
}