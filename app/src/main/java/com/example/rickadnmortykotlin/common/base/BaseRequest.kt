package com.example.rickadnmortykotlin.common.base

import kotlinx.coroutines.flow.MutableStateFlow

interface BaseRequest {
    var page: Int
    fun fetchCharacters(page: Int)
    fun fetchEpisodes(page: Int)
    fun fetchLocations(page: Int)
}