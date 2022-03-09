package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.LocationRepository
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(private val repository: LocationRepository) {
    operator fun invoke(page: Int) = repository.fetchLocations(page)
}