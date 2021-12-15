package com.example.rickadnmortykotlin.ui.fragments.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.base.fragment.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.repositories.EpisodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val repository: EpisodesRepository): BaseViewModel() {

    fun fetchEpisodes() = repository.fetchEpisodes().cachedIn(viewModelScope)

    fun fetchEpisode(id:Int):LiveData<EpisodesModel> {
        return repository.fetchEpisode(id)
    }


}