package com.example.rickadnmortykotlin.data.network.dtos.episodes

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class EpisodesModel(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("air_date")
    val air_date:String,
    @SerializedName("episode")
    val episode:String
): IBaseDiffModel {
}