package com.example.rickadnmortykotlin.data.network.dtos.characters

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.google.gson.annotations.SerializedName

data class CharactersModel(
    override var id:Int,
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
    val gender:String
): IBaseDiffModel
