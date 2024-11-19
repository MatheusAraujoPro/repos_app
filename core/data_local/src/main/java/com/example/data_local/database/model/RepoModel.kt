package com.example.data_local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.model.Repo

@Entity(tableName = "repo")
data class RepoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val repositoryId: Int,
    val name: String,
    val description: String?,
    val language: String?
)

fun RepoModel.toDomainRepo(): Repo {
    return Repo(
        id = repositoryId,
        name = name,
        description = description,
        language = language,
        localID = id
    )
}