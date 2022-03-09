package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchCharactersUseCase @Inject constructor(private val repository: CharactersRepository){
    operator fun invoke(page: Int) = repository.fetchCharacters(page)
}