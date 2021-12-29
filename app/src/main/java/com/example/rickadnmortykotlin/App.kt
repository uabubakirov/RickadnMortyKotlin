package com.example.rickadnmortykotlin

import android.app.Application
import com.example.rickadnmortykotlin.servicelocator.networkModule
import com.example.rickadnmortykotlin.servicelocator.repositoriesModule
import com.example.rickadnmortykotlin.servicelocator.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModule, viewModelsModule)
        }
    }
}