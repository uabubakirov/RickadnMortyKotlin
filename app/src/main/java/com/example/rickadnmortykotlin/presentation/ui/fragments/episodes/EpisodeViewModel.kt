package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.repositories.EpisodesRepository
import com.example.rickadnmortykotlin.presentation.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class EpisodeViewModel constructor(private val repository: EpisodesRepository): BaseViewModel() {

    fun fetchEpisodes() = repository.fetchEpisodes().cachedIn(viewModelScope)

    private val _dataEpisode = MutableStateFlow<UIState<EpisodesModel>>(UIState.Loading())
    val dataEpisode: StateFlow<UIState<EpisodesModel>> = _dataEpisode

    fun fetchEpisode(id:Int) {
        _dataEpisode.subscribeTo {
            repository.fetchEpisode(id) }
    }
}