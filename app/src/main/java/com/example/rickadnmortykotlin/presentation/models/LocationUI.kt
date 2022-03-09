package com.example.rickadnmortykotlin.presentation.models

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.example.rickadnmortykotlin.domain.models.LocationModel

data class LocationUI (
        override val id: Int,
        val name:String,
        val type:String,
        val dimension: String
        ):IBaseDiffModel

fun LocationModel.toUI() = LocationUI(
        id,name,type,dimension
)