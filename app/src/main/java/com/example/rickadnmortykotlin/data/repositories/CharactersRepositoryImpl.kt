package com.example.rickadnmortykotlin.data.repositories


import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickadnmortykotlin.common.base.BaseRepository
import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.data.network.apiservices.CharactersApi
import com.example.rickadnmortykotlin.data.network.dtos.characters.toDomain
import com.example.rickadnmortykotlin.domain.models.CharactersModel
import com.example.rickadnmortykotlin.domain.repositories.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharactersRepositoryImpl @Inject constructor(
    private val service: CharactersApi
) : BaseRepository(),CharactersRepository {


    override fun fetchCharacters(page: Int) = doRequest {
        service.fetchCharacters(page).results.map { it.toDomain() }
    }

    override fun fetchCharacter(id: Int) = doRequest {
        service.fetchCharacter(id).toDomain()
    }

    override fun fetchCharactersByGenderAndGender(
        page: Int,
        gender: String?,
        status: String?
    ) = doRequest {
        service.fetchCharactersByGenderAndStatus(gender,status,page).results.map { it.toDomain() }
    }

    override fun fetchCharacterBySearch(name: String,page: Int) = doRequest {
        service.fetchBySearchCharacters(name,page).results.map { it.toDomain() }
    }

}

