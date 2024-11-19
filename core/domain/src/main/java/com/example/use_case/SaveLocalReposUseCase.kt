package com.example.use_case

import com.example.model.Repo
import com.example.repository.RepoRepository

class SaveLocalReposUseCase(
    private val repository: RepoRepository
) {
    suspend operator fun invoke(repo: Repo){
         repository.saveLocalRepos(repo = repo)
    }
}