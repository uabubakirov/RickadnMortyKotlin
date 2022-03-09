package com.example.rickadnmortykotlin.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import com.example.rickadnmortykotlin.data.network.dtos.locations.toDomain
import com.example.rickadnmortykotlin.domain.models.LocationModel
import com.example.rickadnmortykotlin.domain.repositories.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocationsRepositoryImpl @Inject constructor(private val service: LocationApi):BaseRepository(),LocationRepository {

    override fun fetchLocations(page: Int) = doRequest{
        service.fetchLocations(page).results.map { it.toDomain() }
    }

    override fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id).toDomain()
    }


}