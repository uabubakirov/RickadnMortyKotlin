package com.example.rickadnmortykotlin.presentation.ui.fragments.characters.detailCharacter

import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.domain.usecases.FetchCharacterUseCase
import com.example.rickadnmortykotlin.presentation.models.CharactersUI
import com.example.rickadnmortykotlin.presentation.models.toUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(private val fetchCharacterUseCase: FetchCharacterUseCase): BaseViewModel() {

    private val _stateCharacterDetail = MutableStateFlow<UIState<CharactersUI>>(UIState.Loading())
    val stateCharacterDetail: StateFlow<UIState<CharactersUI>> = _stateCharacterDetail

    fun fetchCharacter(id:Int){
        fetchCharacterUseCase(id).collectRequest(_stateCharacterDetail){it.toUI()}
    }
}