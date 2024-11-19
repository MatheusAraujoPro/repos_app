package com.example.use_case

import com.example.model.Repo
import com.example.repository.RepoRepository

class GetLocalReposUseCase(private val repository: RepoRepository) {
    suspend operator fun invoke(): List<Repo>{
        return repository.getLocalRepos()
    }
}