package com.example.rickadnmortykotlin.common.base

import androidx.lifecycle.liveData
import com.example.rickadnmortykotlin.common.resource.Resource
import kotlinx.coroutines.Dispatchers


abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        }catch (ioException: Exception){
            emit(Resource.Error(data = null, message = ioException.localizedMessage ?: "ErrorOccurred"))
        }
    }

}




