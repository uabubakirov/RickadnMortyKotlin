package com.example.rickadnmortykotlin.presentation.ui.fragments.characters

import com.example.rickadnmortykotlin.common.base.BaseRequest
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.domain.usecases.FetchCharacterUseCase
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
    private val fetchCharactersUseCase: FetchCharactersUseCase
): BaseViewModel(),BaseRequest {

    private val _stateCharacters = MutableStateFlow<UIState<List<CharactersUI>>>(UIState.Loading())
    val stateCharacters: StateFlow<UIState<List<CharactersUI>>> = _stateCharacters

    override var page: Int = 1
    var count = 0
    init {
        fetchCharacters(page)
    }

    override fun fetchCharacters(page: Int){
        fetchCharactersUseCase(page).collectRequest(_stateCharacters){it.map { data-> data.toUI() }}

    }

    override fun fetchEpisodes(page: Int) {
        TODO("Not yet implemented")
    }

    override fun fetchLocations(page: Int) {
        TODO("Not yet implemented")
    }




}