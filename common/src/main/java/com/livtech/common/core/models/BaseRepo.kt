package com.livtech.common.core.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.livtech.common.core.network.ApiClient
import com.livtech.common.core.network.ErrorMessageParser
import com.livtech.common.core.network.ErrorMessageParserImpl
import com.livtech.common.core.utils.DefaultDispatcherProvider
import com.livtech.common.core.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import retrofit2.Response

open class BaseRepo(
    val dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider(),
    private val errorParser: ErrorMessageParser = ErrorMessageParserImpl()
) {

     fun <T> makeApiCall(req: Deferred<Response<T>>): LiveData<Resource<T?>> {
        return liveData {
            try {
                emit(Resource.Loading())
                val call = suspend { req.await() }
                val result = call.invoke().let {
                    if (it.isSuccessful) {
                        if (it.body() == null) {
                            Resource.Failure(errorParser.onApiCallFailure(it), null)
                        } else {
                            Resource.Success(data = it.body())
                        }
                    } else {
                        Resource.Failure(errorParser.onApiCallFailure(it))
                    }
                }
                emit(result)
            } catch (t: Throwable) {
                emit(Resource.Failure( errorParser.onNetworkFailure(t), null))
            }
        }
    }

    fun <T : Any> apiService(classType: Class<T>): T {
        return ApiClient.retrofit.create(classType)
    }
}