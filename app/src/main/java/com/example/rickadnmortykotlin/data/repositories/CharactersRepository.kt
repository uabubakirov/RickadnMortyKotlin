package com.example.rickadnmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickadnmortykotlin.data.network.apiservices.CharactersApi
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class CharactersRepository @Inject constructor(
    private val service: CharactersApi
) {
    fun fetchCharacters(): LiveData<PagingData<CharactersModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                CharacterPaging(service)
            }
        ).liveData
    }

     fun fetchCharacter(id: Int): LiveData<CharactersModel> {
        var data: MutableLiveData<CharactersModel> = MutableLiveData()
        service.fetchCharacter(id).enqueue(object : Callback<CharactersModel> {
            override fun onResponse(
                call: Call<CharactersModel>,
                response: Response<CharactersModel>
            ) {
                if (response.isSuccessful){
                data.value = response.body()
            }
            }

            override fun onFailure(call: Call<CharactersModel>, t: Throwable) {
                data.value = null
            }

        })
        return data

    }
}