package com.example.rickadnmortykotlin.presentation.ui.fragments.locations

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.data.repositories.LocationsRepository
import com.example.rickadnmortykotlin.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LocationViewModel constructor(private val repository:LocationsRepository): BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)

    private val _dataLocations = MutableStateFlow<UIState<LocationsModel>>(UIState.Loading())
    val dataLocations: StateFlow<UIState<LocationsModel>> = _dataLocations

    fun fetchLocation(id: Int) {
        _dataLocations.subscribeTo { repository.fetchLocation(id) }
    }

 }


