package com.example.rickadnmortykotlin.data.network.apiservices

import com.example.rickadnmortykotlin.data.network.dtos.RickAndMortyResponse
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModelDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  CharactersApi {
    @GET("/api/character")
    suspend fun fetchCharacters(@Query("page") page: Int):RickAndMortyResponse<CharactersModelDTO>

    @GET("/api/character/{id}")
    suspend fun fetchCharacter(@Path("id")id: Int): CharactersModelDTO
}