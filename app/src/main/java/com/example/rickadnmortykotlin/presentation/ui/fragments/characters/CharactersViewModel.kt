package com.example.rickadnmortykotlin.presentation.ui.fragments.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.repositories.CharactersRepository
import com.example.rickadnmortykotlin.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharactersViewModel constructor(
    private val repository: CharactersRepository
): BaseViewModel() {

    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)

    private val _data = MutableStateFlow<UIState<CharactersModel>>(UIState.Loading())
    val data: StateFlow<UIState<CharactersModel>> = _data

    fun fetchCharacter(id: Int) {
        _data.subscribeTo { repository.fetchCharacter(id) }
    }
}