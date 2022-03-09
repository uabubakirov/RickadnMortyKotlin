package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(private val repository: CharactersRepository) {

    operator fun invoke(id: Int) = repository.fetchCharacter(id)
}