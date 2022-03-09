package com.example.rickadnmortykotlin.data.network.apiservices

import com.example.rickadnmortykotlin.data.network.dtos.RickAndMortyResponse

import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModelDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationApi {
    @GET("/api/location")
    suspend fun fetchLocations(@Query("page")page:Int):RickAndMortyResponse<LocationsModelDTO>

    @GET("/api/location/{id}")
    suspend fun fetchLocation(@Path("id")id:Int): LocationsModelDTO
}