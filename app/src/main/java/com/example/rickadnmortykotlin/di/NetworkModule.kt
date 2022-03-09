package com.example.rickadnmortykotlin.di

import com.example.rickadnmortykotlin.data.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    val retrofitClient = RetrofitClient()

    @Provides
    @Singleton
    fun provideCharacterApi() = retrofitClient.provideCharacterApi()

    @Provides
    @Singleton
    fun provideLocations() = retrofitClient.provideLocationApi()

    @Provides
    @Singleton
    fun provideEpisodes() = retrofitClient.provideEpisodeApi()
}