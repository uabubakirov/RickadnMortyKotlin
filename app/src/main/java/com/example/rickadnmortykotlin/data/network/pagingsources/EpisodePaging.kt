package com.example.rickadnmortykotlin.data.network.pagingsources

import com.example.rickadnmortykotlin.common.base.BasePaging
import com.example.rickadnmortykotlin.data.network.apiservices.EpisodesApi
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel

class EpisodePaging (private val service: EpisodesApi): BasePaging<EpisodesModel, Any?>({ position->
    service.fetchEpisodes(position)}
)