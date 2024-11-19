package com.example.di

import android.content.Context
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.RepoConstants
import com.example.data_local.data_source.RepoLocalDataSourceImpl
import com.example.data_local.database.Database
import com.example.data_source.RepoLocalDataSource
import com.example.data_source.RepoRemoteDataSource
import com.example.data_source.RepoRemoteDataSourceImpl
import com.example.github.navigation.definition.GithubNavigation
import com.example.github.navigation.implementation.GithubNavigationImpl
import com.example.github.screens.bookmark_screen.BookmarkScreenViewModel
import com.example.github.screens.list_screen.ListScreenViewModel
import com.example.provideGitHubWebService
import com.example.provideRetrofit
import com.example.repository.RepoRepository
import com.example.repository.RepoRepositoryImpl
import com.example.use_case.DeleteLocalRepoUseCase
import com.example.use_case.GetFilteredRepos
import com.example.use_case.GetLocalFavoritedReposUseCase
import com.example.use_case.GetLocalReposUseCase
import com.example.use_case.GetPagingRepos
import com.example.use_case.GetRepos
import com.example.use_case.SaveLocalReposUseCase
import com.example.utils.NavigationManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { get<Context>().preferencesDataStoreFile(RepoConstants.Constants.DATA_STORE_NAME) }
        )
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "repository-database"
        ).build()
    }

    single { get<Database>().repoDao() }
}

val dataSourceModules = module {
    factory<RepoRemoteDataSource> { RepoRemoteDataSourceImpl(get()) }
    factory<RepoLocalDataSource> { RepoLocalDataSourceImpl(get()) }
}

val repositoriesModules = module {
    factory<RepoRepository> { RepoRepositoryImpl(get(), get()) }
}

val viewModelModules = module {
    viewModel { ListScreenViewModel(get(), get(), get(), get(), get()) }
    viewModel { BookmarkScreenViewModel(get(), get()) }
}

val useCaseModules = module {
    factory { GetRepos(get()) }
    factory { GetFilteredRepos() }
    factory { GetPagingRepos(get()) }
    factory { GetLocalReposUseCase(get()) }
    factory { SaveLocalReposUseCase(get()) }
    factory { GetLocalFavoritedReposUseCase(get()) }
    factory { DeleteLocalRepoUseCase(get()) }
}

val networkModel = module {
    single { provideRetrofit() }
    factory { provideGitHubWebService(get()) }
}

val navigationModel = module {
    single {
        NavigationManager(
            CoroutineScope(
                SupervisorJob() + Dispatchers.Main
            )
        )
    }
    single<GithubNavigation> { GithubNavigationImpl(get()) }
}