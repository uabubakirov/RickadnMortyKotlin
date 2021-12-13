package com.example.rickadnmortykotlin.ui.fragments.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickadnmortykotlin.base.fragment.BaseViewModel
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.repositories.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
): BaseViewModel(){
    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)

    var data:MutableLiveData<CharactersModel> = MutableLiveData()

    fun selectModel(model:CharactersModel){
        data.value = model
    }
    fun getModel():LiveData<CharactersModel>{
        return data
    }
}