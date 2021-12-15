package com.example.rickadnmortykotlin.utils

import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel
import com.example.rickadnmortykotlin.data.network.dtos.episodes.EpisodesModel
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel

interface OnItemClick {
    fun onItemCLick(id:Int,name:String)

    fun onItemCLickEpisode(id:Int,name:String)

    fun onItemClickLocation(id:Int,name:String)
}