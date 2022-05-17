package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.CharactersRepository
import javax.inject.Inject

class FetchCharacterBySearchUseCase @Inject constructor(
    private val repository: CharactersRepository
) {

    operator fun invoke(name: String,page: Int) = repository.fetchCharacterBySearch(name,page)
}