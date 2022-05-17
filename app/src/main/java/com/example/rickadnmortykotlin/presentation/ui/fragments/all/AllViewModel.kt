package com.example.rickadnmortykotlin.presentation.ui.fragments.all

import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.domain.usecases.FetchCharacterAllUseCase
import com.example.rickadnmortykotlin.domain.usecases.FetchCharacterBySearchUseCase
import com.example.rickadnmortykotlin.domain.usecases.FetchEpisodeAllUseCase
import com.example.rickadnmortykotlin.domain.usecases.FetchLocationAllUseCase
import com.example.rickadnmortykotlin.presentation.models.AllUI
import com.example.rickadnmortykotlin.presentation.models.CharactersUI
import com.example.rickadnmortykotlin.presentation.models.toUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AllViewModel @Inject constructor(
    private val fetchCharacterAllUseCase: FetchCharacterAllUseCase,
    private val fetchEpisodeAllUseCase: FetchEpisodeAllUseCase,
    private val fetchLocationAllUseCase: FetchLocationAllUseCase
): BaseViewModel() {

    private val _stateFilterCharacters = MutableStateFlow<UIState<List<AllUI>>>(UIState.Loading())
    val stateFilterCharacters: StateFlow<UIState<List<AllUI>>> = _stateFilterCharacters

    private val _stateFilterEp = MutableStateFlow<UIState<List<AllUI>>>(UIState.Loading())
    val stateFilterEp: StateFlow<UIState<List<AllUI>>> = _stateFilterEp

    private val _stateFilterLoc = MutableStateFlow<UIState<List<AllUI>>>(UIState.Loading())
    val stateFilterLoc: StateFlow<UIState<List<AllUI>>> = _stateFilterLoc


    fun fetchCharacterBySearch(name: String){
        fetchCharacterAllUseCase(name).collectRequest(_stateFilterCharacters){it.map { data-> data.toUI() }}
    }


    fun fetchLoc(name: String){
        fetchLocationAllUseCase(name).collectRequest(_stateFilterLoc){it.map { data -> data.toUI() }}

    }
    fun fetchEp(name: String){
        fetchEpisodeAllUseCase(name).collectRequest(_stateFilterEp){it.map { data -> data.toUI() }}

    }

}