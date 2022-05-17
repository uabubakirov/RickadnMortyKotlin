package com.example.rickadnmortykotlin.presentation.models

import com.example.rickadnmortykotlin.common.base.IBaseDiffModel
import com.example.rickadnmortykotlin.domain.models.CharactersModel


data class CharactersUI(
    val name:String,
    val image:String,
    val status:String,
    val species:String,
    val type:String,
    val gender:String, override val id: Int?
):IBaseDiffModel
fun CharactersModel.toUI() = CharactersUI(
    name,
    image,
    status,
    species,
    type,
    gender,id
)
