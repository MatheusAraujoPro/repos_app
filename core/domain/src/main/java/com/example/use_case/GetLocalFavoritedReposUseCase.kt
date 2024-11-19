package com.example.use_case

import com.example.repository.RepoRepository

class GetLocalFavoritedReposUseCase(
    private val repository: RepoRepository
) {
    suspend operator fun invoke(): List<Int> {
        return repository.getStoredRepoIds()
    }
}