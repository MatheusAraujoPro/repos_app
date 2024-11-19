package com.example.use_case

import com.example.model.Repo

class GetFilteredRepos {
    fun filterRepos(repoName: String, repoList: List<Repo>): List<Repo> {
        return repoList.filter { repo -> repo.name.startsWith(repoName)  }
    }
}