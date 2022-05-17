package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.LocationRepository
import javax.inject.Inject

class FetchLocationByAllFilterUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(name: String?,type: String?,dimension: String?) = repository.fetchLocationByAllFilter(name,type,dimension)
}