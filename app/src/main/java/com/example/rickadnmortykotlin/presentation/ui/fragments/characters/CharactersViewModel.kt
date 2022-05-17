package com.example.rickadnmortykotlin.presentation.ui.fragments.characters

import com.example.rickadnmortykotlin.common.base.BaseRequest
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.domain.usecases.FetchCharacterByGenderAndStatusUseCase
import com.example.rickadnmortykotlin.domain.usecases.FetchCharacterBySearchUseCase
import com.example.rickadnmortykotlin.domain.usecases.FetchCharactersUseCase
import com.example.rickadnmortykotlin.presentation.models.CharactersUI
import com.example.rickadnmortykotlin.presentation.models.toUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val fetchCharactersUseCase: FetchCharactersUseCase,
    private val fetchCharacterByGenderAndStatusUseCase: FetchCharacterByGenderAndStatusUseCase,
    private val fetchCharacterBySearchUseCase: FetchCharacterBySearchUseCase
): BaseViewModel() {

    private val _stateCharacters = MutableStateFlow<UIState<List<CharactersUI>>>(UIState.Loading())
    val stateCharacters: StateFlow<UIState<List<CharactersUI>>> = _stateCharacters

    private val _stateFilterCharacters = MutableStateFlow<UIState<List<CharactersUI>>>(UIState.Loading())
    val stateFilterCharacters: StateFlow<UIState<List<CharactersUI>>> = _stateFilterCharacters

    var page: Int = 1
    var count = 0
    init {
        fetchCharacters(page)
    }

    fun fetchCharacters(page: Int){
        fetchCharactersUseCase(page).collectRequest(_stateCharacters){it.map { data-> data.toUI() }}

    }
    fun fetchCharacterBySearch(name: String,page: Int = 1){
        fetchCharacterBySearchUseCase(name,page).collectRequest(_stateFilterCharacters){it.map {data-> data.toUI() }}
    }


    fun fetchCharactersByGenderAndStatus(gender: String?,page: Int,status: String?){
        fetchCharacterByGenderAndStatusUseCase(gender,status,page).collectRequest(_stateFilterCharacters){it.map { data -> data.toUI() }}
    }
}