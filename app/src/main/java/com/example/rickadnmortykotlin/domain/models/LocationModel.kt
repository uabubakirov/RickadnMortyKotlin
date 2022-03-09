package com.example.rickadnmortykotlin.domain.models

import com.google.gson.annotations.SerializedName

data class LocationModel(
    val id: Int,
    val name:String,
    val type:String,
    val dimension: String
)
