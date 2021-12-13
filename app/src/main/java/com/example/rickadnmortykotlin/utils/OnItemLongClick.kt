package com.example.rickadnmortykotlin.utils

import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel

interface OnItemLongClick {
    fun onItemLongCLick(character: CharactersModel)
}