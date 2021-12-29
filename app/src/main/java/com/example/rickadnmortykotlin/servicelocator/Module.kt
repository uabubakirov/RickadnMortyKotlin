package com.example.rickadnmortykotlin.servicelocator

import com.example.rickadnmortykotlin.data.RetrofitClient
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.repositories.CharactersRepository
import com.example.rickadnmortykotlin.data.repositories.EpisodesRepository
import com.example.rickadnmortykotlin.data.repositories.LocationsRepository
import com.example.rickadnmortykotlin.presentation.ui.fragments.characters.CharactersViewModel
import com.example.rickadnmortykotlin.presentation.ui.fragments.episodes.EpisodeViewModel
import com.example.rickadnmortykotlin.presentation.ui.fragments.locations.LocationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().provideCharacterApi() }
    single { get<RetrofitClient>().provideEpisodeApi() }
    single { get<RetrofitClient>().provideLocationApi() }
}

val repositoriesModule = module {
    factory { CharactersRepository(get())}
    factory { LocationsRepository(get())}
    factory { EpisodesRepository(get())}
}

val viewModelsModule = module {
    viewModel{CharactersViewModel(get())}
    viewModel{EpisodeViewModel(get())}
    viewModel{LocationViewModel(get())}

}
