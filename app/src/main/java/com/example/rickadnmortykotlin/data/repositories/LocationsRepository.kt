package com.example.rickadnmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import com.example.rickadnmortykotlin.data.network.pagingsources.LocationPaging
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.CompletionService
import javax.inject.Inject

class LocationsRepository @Inject constructor(private val service: LocationApi):BaseRepository() {

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