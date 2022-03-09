package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.EpisodesRepository
import javax.inject.Inject

class FetchEpisodeUseCase @Inject constructor(private val episodesRepository: EpisodesRepository) {

    operator fun invoke(id: Int) = episodesRepository.fetchEpisode(id)
}