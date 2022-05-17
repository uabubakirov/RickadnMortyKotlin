package com.example.rickadnmortykotlin.domain.models

import com.google.gson.annotations.SerializedName

data class AllModel(
    val id: Int? = null,
    val name:String? = null,
    val image:String? = null,
    val status:String? = null,
    val species:String? = null,
    val type:String? = null,
    val gender:String? = null,
    val air_date:String? = null,
    val dimension: String? = null,
    val created: String? = null
)