package com.example.rickadnmortykotlin.data.network.dtos.characters

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.example.rickadnmortykotlin.domain.models.CharactersModel
import com.google.gson.annotations.SerializedName

data class CharactersModelDTO(
    @SerializedName("name")
    val name:String,
    @SerializedName("image")
    val image:String,
    @SerializedName("status")
    val status:String,
    @SerializedName("species")
    val species:String,
    @SerializedName("type")
    val type:String,
    @SerializedName("gender")
    val gender:String,
    @SerializedName("id")
    val id: Int
)
fun CharactersModelDTO.toDomain()=CharactersModel(
    name,
    image,
    status,
    species,
    type,
    gender,
    id
)
