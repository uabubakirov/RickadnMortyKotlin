package com.example.rickadnmortykotlin.presentation.models

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.example.rickadnmortykotlin.domain.models.EpisodeModel

data class EpisodesUI(
    override val id: Int,
    val name:String,
    val air_date:String,
    val episode:String
):IBaseDiffModel

fun EpisodeModel.toUI() = EpisodesUI(
    id,name,air_date,episode
)
