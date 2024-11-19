package com.example.data_local.data_source

import com.example.data_local.database.dao.RepoDao
import com.example.data_local.database.model.RepoModel
import com.example.data_local.database.model.toDomainRepo
import com.example.data_source.RepoLocalDataSource
import com.example.model.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class RepoLocalDataSourceImpl(
    private val repoDao: RepoDao
) : RepoLocalDataSource {
    override suspend fun getRepos(): List<Repo> {
        return withContext(Dispatchers.IO) {
            repoDao
                .getAll()
                .first()
                .map { repo -> repo.toDomainRepo() }
        }
    }

    override suspend fun saveRepo(repo: Repo) {
        withContext(Dispatchers.IO) {
            repoDao.addRepo(
                RepoModel(
                    repositoryId = repo.id,
                    name = repo.name,
                    description = repo.description,
                    language = repo.language
                )
            )
        }
    }

    override suspend fun getStoredRepoIds(): List<Int> {
        return withContext(Dispatchers.IO) {
            repoDao
                .getStoredRepoIds()
                .first()
        }
    }

    override suspend fun deleteLocalRepo(repo: Repo) {
        withContext(Dispatchers.IO) {
            repoDao.deleteRepo(
                RepoModel(
                    repositoryId = repo.id,
                    name = repo.name,
                    description = repo.description,
                    language = repo.language,
                    id = repo.localID ?: 0
                )
            )
        }
    }
}