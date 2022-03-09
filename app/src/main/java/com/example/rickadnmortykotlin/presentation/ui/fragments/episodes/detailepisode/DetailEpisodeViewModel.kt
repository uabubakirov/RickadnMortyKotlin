package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes.detailepisode

import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.domain.usecases.FetchEpisodeUseCase
import com.example.rickadnmortykotlin.domain.usecases.FetchEpisodesUseCase
import com.example.rickadnmortykotlin.presentation.models.EpisodesUI
import com.example.rickadnmortykotlin.presentation.models.toUI
import com.example.rickadnmortykotlin.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
@HiltViewModel
class DetailEpisodeViewModel @Inject constructor(private val episodeUseCase: FetchEpisodeUseCase): BaseViewModel() {

    private val _stateEpisodeDetail = MutableStateFlow<UIState<EpisodesUI>>(UIState.Loading())
    val stateEpisodeDetail: StateFlow<UIState<EpisodesUI>> = _stateEpisodeDetail


    fun fetchEpisode(id:Int) {
        episodeUseCase(id).collectRequest(_stateEpisodeDetail){it.toUI()}
    }

}