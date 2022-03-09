package com.example.rickadnmortykotlin.data.network.apiservices

import com.example.rickadnmortykotlin.data.network.dtos.RickAndMortyResponse
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModelDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesApi {
    @GET("/api/episode")
    suspend fun fetchEpisodes(@Query("page")page:Int):RickAndMortyResponse<EpisodesModelDTO>

    @GET("/api/episode/{id}")
    suspend fun fetchEpisode(@Path("id")id:Int): EpisodesModelDTO
}