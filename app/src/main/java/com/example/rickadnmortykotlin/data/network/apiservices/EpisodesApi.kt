package com.example.rickadnmortykotlin.data.network.apiservices

import com.example.rickadnmortykotlin.data.network.dtos.RickAndMortyResponse
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesApi {
    @GET("/api/episode")
    suspend fun fetchEpisodes(@Query("page")page:Int):RickAndMortyResponse<EpisodesModel>

    @GET("/api/episode/{id}")
    fun fetchEpisode(@Path("id")id:Int): Call<EpisodesModel>
}