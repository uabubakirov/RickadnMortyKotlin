package com.example.rickadnmortykotlin.data.network.apiservices

import com.example.rickadnmortykotlin.data.network.dtos.RickAndMortyResponse
import com.example.rickadnmortykotlin.data.network.dtos.all.AllDTO
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModelDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AllApi {
    @GET("/api/character")
    suspend fun fetchCharacters(@Query("name") name: String): RickAndMortyResponse<AllDTO>

    @GET("/api/episode")
    suspend fun fetchEpisodes(@Query("name") name: String): RickAndMortyResponse<AllDTO>

    @GET("/api/location")
    suspend fun fetchLocation(@Query("name") name: String): RickAndMortyResponse<AllDTO>
}

