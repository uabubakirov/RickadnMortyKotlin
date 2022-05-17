package com.example.rickadnmortykotlin.domain.repositories

import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun fetchLocations(page: Int): Flow<Resource<List<LocationModel>>>

    fun fetchLocation(id: Int): Flow<Resource<LocationModel>>

    fun fetchLocationByAllFilter(name: String?,type: String?,dimension: String?): Flow<Resource<List<LocationModel>>>
}