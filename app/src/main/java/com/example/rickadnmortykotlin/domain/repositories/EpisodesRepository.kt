package com.example.rickadnmortykotlin.domain.repositories

import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.domain.models.CharactersModel
import com.example.rickadnmortykotlin.domain.models.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository  {

    fun fetchEpisodes(page: Int): Flow<Resource<List<EpisodeModel>>>

    fun fetchEpisode(id: Int): Flow<Resource<EpisodeModel>>

    fun fetchEpisodesByEpisodeAndName(name: String?,episode: String?): Flow<Resource<List<EpisodeModel>>>

}