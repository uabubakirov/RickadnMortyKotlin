package com.example.rickadnmortykotlin.data.network.pagingsources

import com.example.rickadnmortykotlin.base.pagging.BasePaging
import com.example.rickadnmortykotlin.data.network.apiservices.EpisodesApi
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel

class EpisodePaging (private val service: EpisodesApi): BasePaging<EpisodesModel>({ position->
    service.fetchEpisodes(position)}
) {
}