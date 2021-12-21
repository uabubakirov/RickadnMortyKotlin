package com.example.rickadnmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickadnmortykotlin.common.base.BasePaging
import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.data.network.apiservices.CharactersApi
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject



class CharactersRepository @Inject constructor(
    private val service: CharactersApi
):BaseRepository() {

    fun fetchCharacters(): LiveData<PagingData<CharactersModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                CharacterPaging(service)
            }
        ).liveData
    }

    fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id)
    }
}

