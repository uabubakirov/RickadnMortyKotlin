package com.example.rickadnmortykotlin.data.repositories


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.data.network.apiservices.CharactersApi
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import kotlinx.coroutines.flow.Flow



class CharactersRepository constructor(
    private val service: CharactersApi
) : BaseRepository() {

    fun fetchCharacters(): Flow<PagingData<CharactersModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                CharacterPaging(service)
            }
        ).flow
    }

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }
}

