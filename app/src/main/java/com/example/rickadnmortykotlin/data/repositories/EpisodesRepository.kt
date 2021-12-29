package com.example.rickadnmortykotlin.data.repositories


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData

import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.data.network.apiservices.EpisodesApi
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.network.pagingsources.EpisodePaging
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val service:EpisodesApi):BaseRepository() {

    fun fetchEpisodes(): Flow<PagingData<EpisodesModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                EpisodePaging(service)
            }
        ).flow
    }
    fun fetchEpisode(id:Int) = doRequest {
        service.fetchEpisode(id)
    }
}