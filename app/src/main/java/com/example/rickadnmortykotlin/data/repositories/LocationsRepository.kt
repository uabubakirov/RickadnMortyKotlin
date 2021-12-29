package com.example.rickadnmortykotlin.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.data.network.pagingsources.LocationPaging
import kotlinx.coroutines.flow.Flow


class LocationsRepository constructor(private val service: LocationApi):BaseRepository() {

    fun fetchLocations():Flow<PagingData<LocationsModel>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                LocationPaging(service)
            }
        ).flow
    }

    fun fetchLocation(id: Int) = doRequest {
        service.fetchLocation(id)
    }
}