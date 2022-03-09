package com.example.rickadnmortykotlin.data.repositories

import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.data.network.apiservices.EpisodesApi
import com.example.rickadnmortykotlin.data.network.dtos.episodes.toDomain
import com.example.rickadnmortykotlin.domain.models.EpisodeModel
import com.example.rickadnmortykotlin.domain.repositories.EpisodesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class EpisodesRepositoryImpl @Inject constructor(private val service:EpisodesApi):BaseRepository(),EpisodesRepository {

    override fun fetchEpisodes(page: Int) = doRequest {
        service.fetchEpisodes(page).results.map { it.toDomain() }
    }

    override fun fetchEpisode(id: Int) = doRequest {
        service.fetchEpisode(id).toDomain()
    }


}