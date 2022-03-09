package com.example.rickadnmortykotlin.data.network.dtos.episodes

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.example.rickadnmortykotlin.domain.models.EpisodeModel
import com.google.gson.annotations.SerializedName

data class EpisodesModelDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("air_date")
    val air_date:String,
    @SerializedName("episode")
    val episode:String
)

fun EpisodesModelDTO.toDomain() = EpisodeModel(
    id,name,air_date,episode
)