package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.common.base.BaseRequest
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModelDTO
import com.example.rickadnmortykotlin.data.repositories.EpisodesRepositoryImpl
import com.example.rickadnmortykotlin.domain.usecases.FetchEpisodeByNameAndEpisodeUseCase
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
class EpisodeViewModel @Inject constructor(
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
    private val fetchEpisodeByNameAndEpisodeUseCase: FetchEpisodeByNameAndEpisodeUseCase): BaseViewModel(){


    private val _dataEpisode = MutableStateFlow<UIState<List<EpisodesUI>>>(UIState.Loading())
    val dataEpisode: StateFlow<UIState<List<EpisodesUI>>> = _dataEpisode

    private val _dataEpisodeFilter = MutableStateFlow<UIState<List<EpisodesUI>>>(UIState.Loading())
    val dataEpisodeFilter: StateFlow<UIState<List<EpisodesUI>>> = _dataEpisodeFilter

    var page: Int = 1
    var count = 0

    init {
        fetchEpisodes(page)
    }

    fun fetchEpisodes(page: Int){
        fetchEpisodesUseCase(page).collectRequest(_dataEpisode){ it.map { data -> data.toUI() }}
    }

    fun fetchEpisodeByFilter(name: String?,episode: String?){
        fetchEpisodeByNameAndEpisodeUseCase(name,episode).collectRequest(_dataEpisodeFilter){it.map { data -> data.toUI() }}
    }




}