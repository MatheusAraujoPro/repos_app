package com.example.data_local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data_local.database.model.RepoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRepo(repoModel: RepoModel)
    @Query("SELECT * FROM repo")
    fun getAll(): Flow<List<RepoModel>>
    @Query("SELECT repositoryId FROM repo")
    fun getStoredRepoIds(): Flow<List<Int>>
    @Delete
    fun deleteRepo(vararg repoModel: RepoModel)
}