package com.example.rickadnmortykotlin.domain.repositories

import com.example.rickadnmortykotlin.common.resource.Resource
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModelDTO
import com.example.rickadnmortykotlin.domain.models.CharactersModel
import com.example.rickadnmortykotlin.presentation.models.AllUI
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun fetchCharacters(page: Int): Flow<Resource<List<CharactersModel>>>

    fun fetchCharacter(id: Int): Flow<Resource<CharactersModel>>

    fun fetchCharactersByGenderAndGender(
        page: Int,
        gender: String?,
        status: String?
    ): Flow<Resource<List<CharactersModel>>>

    fun fetchCharacterBySearch(name: String, page: Int): Flow<Resource<List<CharactersModel>>>

}
