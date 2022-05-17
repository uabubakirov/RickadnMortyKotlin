package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.EpisodesRepository
import javax.inject.Inject

class FetchEpisodeByNameAndEpisodeUseCase @Inject constructor(
    private val repository: EpisodesRepository
) {
    operator fun invoke(name: String?,episode: String?) = repository.fetchEpisodesByEpisodeAndName(name,episode)
}