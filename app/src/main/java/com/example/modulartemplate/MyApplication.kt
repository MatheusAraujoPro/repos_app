package com.example.modulartemplate

import android.app.Application
import com.example.di.appModules
import com.example.di.dataSourceModules
import com.example.di.databaseModule
import com.example.di.navigationModel
import com.example.di.networkModel
import com.example.di.repositoriesModules
import com.example.di.useCaseModules
import com.example.di.viewModelModules
import com.example.modulartemplate.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                appModules,
                dataSourceModules,
                repositoriesModules,
                networkModel,
                viewModelModules,
                useCaseModules,
                navigationModel,
                mainModule,
                databaseModule
            )
        }.androidContext(applicationContext)
    }
}