package com.example.rickadnmortykotlin.data.network.dtos.locations

import com.example.rickadnmortykotlin.base.adapter.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class LocationsModel(
    @SerializedName("id")
    override val id: Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("type")
    val type:String,
    @SerializedName("dimension")
    val dimension: String

): IBaseDiffModel
