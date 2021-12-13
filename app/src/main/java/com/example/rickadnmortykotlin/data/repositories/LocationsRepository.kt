package com.example.rickadnmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import com.example.rickadnmortykotlin.data.network.pagingsources.LocationPaging
import java.util.concurrent.CompletionService
import javax.inject.Inject

class LocationsRepository @Inject constructor(private val service: LocationApi) {

    fun fetchLocations():LiveData<PagingData<LocationsModel>>{
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                LocationPaging(service)
            }
        ).liveData
    }
}