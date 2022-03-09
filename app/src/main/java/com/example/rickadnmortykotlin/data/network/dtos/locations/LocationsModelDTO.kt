package com.example.rickadnmortykotlin.data.network.dtos.locations

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.example.rickadnmortykotlin.domain.models.LocationModel
import com.google.gson.annotations.SerializedName

data class LocationsModelDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("type")
    val type:String,
    @SerializedName("dimension")
    val dimension: String

)
fun LocationsModelDTO.toDomain() = LocationModel(
    id,name,type,dimension
)