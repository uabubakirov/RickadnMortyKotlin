package com.example.rickadnmortykotlin.data.repositories

import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.data.network.apiservices.AllApi
import com.example.rickadnmortykotlin.data.network.dtos.all.toDomain
import com.example.rickadnmortykotlin.data.network.dtos.characters.toDomain
import com.example.rickadnmortykotlin.domain.models.AllModel
import com.example.rickadnmortykotlin.domain.repositories.AllRepo
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorCompletionService
import javax.inject.Inject

class AllRepoImpl @Inject constructor(
    private val service: AllApi
) : BaseRepository(),AllRepo {


    override fun fetchCharacter(name: String) = doRequest {
        service.fetchCharacters(name).results.map { it.toDomain() }
    }

    override fun fetchEpisodes(name: String) = doRequest {
        service.fetchEpisodes(name).results.map { it.toDomain() }
    }

    override fun fetchLocations(name: String) = doRequest {
        service.fetchLocation(name).results.map { it.toDomain() }
    }
}