package com.example.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_local.database.dao.RepoDao
import com.example.data_local.database.model.RepoModel

@Database(
    entities = [ RepoModel::class ],
    version = 1
)
abstract class Database: RoomDatabase() {
    abstract fun repoDao(): RepoDao
}