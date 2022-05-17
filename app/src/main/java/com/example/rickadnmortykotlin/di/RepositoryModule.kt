package com.example.rickadnmortykotlin.di

import com.example.rickadnmortykotlin.data.repositories.AllRepoImpl
import com.example.rickadnmortykotlin.data.repositories.CharactersRepositoryImpl
import com.example.rickadnmortykotlin.data.repositories.EpisodesRepositoryImpl
import com.example.rickadnmortykotlin.data.repositories.LocationsRepositoryImpl
import com.example.rickadnmortykotlin.domain.repositories.AllRepo
import com.example.rickadnmortykotlin.domain.repositories.CharactersRepository
import com.example.rickadnmortykotlin.domain.repositories.EpisodesRepository
import com.example.rickadnmortykotlin.domain.repositories.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn((SingletonComponent::class))
abstract class RepositoryModule {

    @Binds
    abstract fun provideCharacterRepository(
        charactersRepositoryImpl: CharactersRepositoryImpl
    ): CharactersRepository

    @Binds
    abstract fun provideEpisodeRepository(
        episodeRepositoryImpl: EpisodesRepositoryImpl
    ): EpisodesRepository

    @Binds
    abstract fun provideLocationRepository(
        locationRepositoryImpl: LocationsRepositoryImpl
    ): LocationRepository

    @Binds
    abstract fun provideAllRepository(
        allRepoImpl: AllRepoImpl
    ): AllRepo

}