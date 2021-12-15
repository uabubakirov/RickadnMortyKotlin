package com.example.rickadnmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import com.example.rickadnmortykotlin.data.network.pagingsources.LocationPaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    fun fetchLocation(id: Int):LiveData<LocationsModel>{
        var data: MutableLiveData<LocationsModel> = MutableLiveData()
        service.fetchLocation(id).enqueue(object : Callback<LocationsModel>{
            override fun onResponse(
                call: Call<LocationsModel>,
                response: Response<LocationsModel>
            ) {
                if (response.isSuccessful){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<LocationsModel>, t: Throwable) {
                data.value = null
            }

        })
        return data
    }
}