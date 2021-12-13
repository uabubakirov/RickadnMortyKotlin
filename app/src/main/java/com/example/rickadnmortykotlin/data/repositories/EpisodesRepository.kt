package com.example.rickadnmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickadnmortykotlin.data.network.apiservices.EpisodesApi
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import com.example.rickadnmortykotlin.data.network.pagingsources.EpisodePaging
import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val service:EpisodesApi) {

    fun fetchEpisodes(): LiveData<PagingData<EpisodesModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                EpisodePaging(service)
            }
        ).liveData
    }
}