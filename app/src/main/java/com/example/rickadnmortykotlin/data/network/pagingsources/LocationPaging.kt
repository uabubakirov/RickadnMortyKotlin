package com.example.rickadnmortykotlin.data.network.pagingsources

import com.example.rickadnmortykotlin.base.pagging.BasePaging
import com.example.rickadnmortykotlin.data.network.apiservices.LocationApi
import com.example.rickadnmortykotlin.data.network.dtos.locations.LocationsModel

class LocationPaging(private val service:LocationApi): BasePaging<LocationsModel>({ position->
    service.fetchLocations(position)}
) {
}