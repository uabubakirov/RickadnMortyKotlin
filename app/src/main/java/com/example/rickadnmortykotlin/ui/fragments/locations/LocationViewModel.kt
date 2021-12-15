package com.example.rickadnmortykotlin.ui.fragments.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.base.fragment.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel
import com.example.rickadnmortykotlin.data.repositories.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val repository:LocationsRepository): BaseViewModel() {

    fun fetchLocations() = repository.fetchLocations().cachedIn(viewModelScope)

    fun fetchLocation(id: Int):LiveData<LocationsModel>{
        return repository.fetchLocation(id)
    }

}