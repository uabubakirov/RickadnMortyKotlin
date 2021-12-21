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



//protected fun <ValueDto : Any,Value : Any> doPagingRequest(
//    pagingSource: BasePaging<ValueDto,Value>,
//    pageSize: Int = 10,
//    prefetchDistance: Int = pageSize,
//    enablePlaceholders: Boolean = true,
//    initialLoadSize: Int = pageSize * 3,
//    maxSize: Int = Int.MAX_VALUE,
//    jumpThreshold: Int = Int.MIN_VALUE
//
//): LiveData<PagingData<ValueDto>> {
//    return Pager(
//        config = PagingConfig(
//            pageSize,
//            prefetchDistance,
//            enablePlaceholders,
//            initialLoadSize,
//            maxSize,
//            jumpThreshold
//        ),
//        pagingSourceFactory = {
//            pagingSource
//        }
//    ).liveData
//}
