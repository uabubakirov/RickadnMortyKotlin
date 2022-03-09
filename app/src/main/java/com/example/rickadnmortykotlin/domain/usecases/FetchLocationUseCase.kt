package com.example.rickadnmortykotlin.domain.usecases

import com.example.rickadnmortykotlin.domain.repositories.LocationRepository
import javax.inject.Inject

class FetchLocationUseCase @Inject constructor(private val repository: LocationRepository) {

    operator fun invoke(id: Int) = repository.fetchLocation(id)
}