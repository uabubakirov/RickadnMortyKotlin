package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.EpisodesRepository
import javax.inject.Inject

class FetchEpisodesUseCase @Inject constructor(private val episodesRepository: EpisodesRepository) {

    operator fun invoke(page: Int) = episodesRepository.fetchEpisodes(page)
}