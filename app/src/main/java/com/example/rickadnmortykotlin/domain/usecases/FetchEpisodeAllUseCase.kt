package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.AllRepo
import javax.inject.Inject

class FetchEpisodeAllUseCase @Inject constructor(
    private val repository: AllRepo
){
    operator fun invoke(name: String) = repository.fetchEpisodes(name)
}