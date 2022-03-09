package com.example.rickadnmortykotlin.presentation.ui.fragments.locations

import com.example.rickadnmortykotlin.common.base.BaseRequest
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.domain.usecases.FetchLocationsUseCase
import com.example.rickadnmortykotlin.presentation.models.LocationUI
import com.example.rickadnmortykotlin.presentation.models.toUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val locationsUseCase: FetchLocationsUseCase): BaseViewModel(),BaseRequest {

    private val _stateLocationsDetail = MutableStateFlow<UIState<List<LocationUI>>>(UIState.Loading())
    val stateLocations: StateFlow<UIState<List<LocationUI>>> = _stateLocationsDetail
    override var page: Int = 1
    var count = 0
    init {
        fetchLocations(page)
    }

    override fun fetchLocations(page: Int) {
        locationsUseCase(page).collectRequest(_stateLocationsDetail){it.map { data ->data.toUI() }}
    }


    override fun fetchCharacters(page: Int) {
        TODO("Not yet implemented")
    }

    override fun fetchEpisodes(page: Int) {
        TODO("Not yet implemented")
    }

 }


