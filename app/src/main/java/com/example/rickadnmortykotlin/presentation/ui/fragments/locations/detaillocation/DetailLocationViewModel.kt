package com.example.rickadnmortykotlin.presentation.ui.fragments.locations.detaillocation

import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.domain.usecases.FetchLocationUseCase
import com.example.rickadnmortykotlin.presentation.models.LocationUI
import com.example.rickadnmortykotlin.presentation.models.toUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailLocationViewModel @Inject constructor(private val locationUseCase: FetchLocationUseCase): BaseViewModel() {

    private val _stateLocationsDetail = MutableStateFlow<UIState<LocationUI>>(UIState.Loading())
    val stateLocationsDetail: StateFlow<UIState<LocationUI>> = _stateLocationsDetail

    fun fetchLocation(id:Int){
        locationUseCase(id).collectRequest(_stateLocationsDetail){it.toUI()}
    }
}