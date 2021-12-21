package com.example.rickadnmortykotlin.data.network.pagingsources

import com.example.rickadnmortykotlin.common.base.BasePaging
import com.example.rickadnmortykotlin.data.network.apiservices.CharactersApi
import com.example.rickadnmortykotlin.data.network.dtos.characters.CharactersModel

class CharacterPaging(private val service:CharactersApi): BasePaging<CharactersModel, Any?>({ position ->
    service.fetchCharacters(position)
}){
}