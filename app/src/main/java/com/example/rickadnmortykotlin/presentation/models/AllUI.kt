package com.example.rickadnmortykotlin.presentation.models

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.example.rickadnmortykotlin.domain.models.AllModel

data class AllUI(
    override val id: Int? = null,
    val name:String? = null,
    val image:String? = null,
    val status:String? = null,
    val species:String? = null,
    val type:String? = null,
    val gender:String? = null,
    val air_date:String? = null,
    val dimension: String? = null,
    val created: String? = null
):IBaseDiffModel

fun AllModel.toUI() = AllUI(
    id,name, image, status, species, type, gender, air_date, dimension,created
)
