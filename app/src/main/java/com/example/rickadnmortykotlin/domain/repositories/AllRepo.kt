package com.example.rickadnmortykotlin.domain.repositories

import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.domain.models.AllModel
import com.example.rickadnmortykotlin.domain.models.CharactersModel
import com.example.rickadnmortykotlin.domain.models.EpisodeModel
import com.example.rickadnmortykotlin.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow

interface AllRepo {

    fun fetchCharacter(name: String): Flow<Resource<List<AllModel>>>

    fun fetchEpisodes(name: String): Flow<Resource<List<AllModel>>>

    fun fetchLocations(name: String): Flow<Resource<List<AllModel>>>

}