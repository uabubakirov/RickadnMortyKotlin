package com.example.rickadnmortykotlin.utils

import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel

interface OnItemClick {
    fun onItemCLick(character:CharactersModel,name:String)

    fun onItemCLickEpisode(episode:EpisodesModel,name:String)

    fun onItemClickLocation(location:LocationsModel,name:String)
}