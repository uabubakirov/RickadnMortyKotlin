package com.example.rickadnmortykotlin.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.rickadnmortykotlin.data.network.apiservices.EpisodesApi
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.network.pagingsources.CharacterPaging
import com.example.rickadnmortykotlin.data.network.pagingsources.EpisodePaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val service:EpisodesApi) {

    fun fetchEpisodes(): LiveData<PagingData<EpisodesModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                EpisodePaging(service)
            }
        ).liveData
    }
    fun fetchEpisode(id:Int):LiveData<EpisodesModel>{
        val data:MutableLiveData<EpisodesModel> = MutableLiveData()
        service.fetchEpisode(id).enqueue(object :Callback<EpisodesModel>{
            override fun onResponse(call: Call<EpisodesModel>, response: Response<EpisodesModel>) {
                if(response.isSuccessful){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<EpisodesModel>, t: Throwable) {
                data.value = null
            }

        })
        return data
    }
}