package com.example.rickadnmortykotlin.data.network.pagingsources

import com.example.rickadnmortykotlin.common.base.BasePaging
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel

class LocationPaging(private val service:LocationApi): BasePaging<LocationsModel, Any?>({ position->
    service.fetchLocations(position)}
)