package com.example.rickadnmortykotlin.domain.models

import com.google.gson.annotations.SerializedName

data class EpisodeModel(
    val id: Int,
    val name:String,
    val air_date:String,
    val episode:String
)
