package com.example.rickadnmortykotlin.presentation.ui.fragments.episodes

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.common.base.BaseRequest
import com.example.rickadnmortykotlin.common.base.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModelDTO
import com.example.rickadnmortykotlin.data.repositories.EpisodesRepositoryImpl
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
    private val fetchEpisodesUseCase: FetchEpisodesUseCase): BaseViewModel(),BaseRequest {


    private val _dataEpisode = MutableStateFlow<UIState<List<EpisodesUI>>>(UIState.Loading())
    val dataEpisode: StateFlow<UIState<List<EpisodesUI>>> = _dataEpisode

    override var page: Int = 1
    var count = 0

    init {
        fetchEpisodes(page)
    }

    override fun fetchEpisodes(page: Int){
        fetchEpisodesUseCase(page).collectRequest(_dataEpisode){ it.map { data -> data.toUI() }}
    }

    override fun fetchLocations(page: Int) {
        TODO("Not yet implemented")
    }

    override fun fetchCharacters(page: Int) {
        TODO("Not yet implemented")
    }




}