package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchCharacterByGenderAndStatusUseCase @Inject constructor(
    private val repository: CharactersRepository
){
    operator fun invoke(gender:String?,status: String?,page: Int) = repository.fetchCharactersByGenderAndGender(page,gender,status)
}