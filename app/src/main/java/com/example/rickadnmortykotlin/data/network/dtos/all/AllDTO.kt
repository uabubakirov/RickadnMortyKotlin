package com.example.rickadnmortykotlin.data.network.dtos.all

import com.example.rickadnmortykotlin.domain.models.AllModel
import com.google.gson.annotations.SerializedName

data class AllDTO(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name:String? = null,
    @SerializedName("image")
    val image:String? = null,
    @SerializedName("status")
    val status:String? = null,
    @SerializedName("species")
    val species:String? = null,
    @SerializedName("type")
    val type:String? = null,
    @SerializedName("gender")
    val gender:String? = null,
    @SerializedName("air_date")
    val air_date:String? = null,
    @SerializedName("dimension")
    val dimension: String? = null,
    @SerializedName("created")
    val created: String? = null
)
fun AllDTO.toDomain() = AllModel(
    id ,name, image, status, species, type, gender, air_date, dimension,created
)
