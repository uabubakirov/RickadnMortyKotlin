package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.AllRepo
import com.example.rickadnmortykotlin.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchCharacterAllUseCase @Inject constructor(
    private val repository: AllRepo
){
    operator fun invoke(name: String) = repository.fetchCharacter(name)
}