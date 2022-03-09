package com.example.rickadnmortykotlin.domain.models

import com.google.gson.annotations.SerializedName

data class CharactersModel(
    val name:String,
    val image:String,
    val status:String,
    val species:String,
    val type:String,
    val gender:String,
    val id: Int
)
