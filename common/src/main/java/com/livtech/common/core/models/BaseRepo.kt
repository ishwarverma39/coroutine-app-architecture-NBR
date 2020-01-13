package com.livtech.common.core.models

import com.livtech.common.core.network.ApiClient
import com.livtech.common.core.network.ErrorMessageParser
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

open class BaseRepo (val scope: CoroutineScope, private val errorParser: ErrorMessageParser = ErrorMessageParser()){

    suspend fun <T> makeApiCall(call: suspend () -> Response<T>): Resource<T?> {
        return try {
            call.invoke().let {
                if (it.isSuccessful) {
                    if (it.body() == null) {
                        Resource.Error(null, errorParser.onApiCallFailed(it))
                    } else {
                        Resource.Success(it.body())
                    }
                } else {
                    Resource.Error(null, errorParser.onApiCallFailed(it))
                }
            }
        } catch (t: Throwable) {
            Resource.Error(null, errorParser.onNetworkError(t))
        }
    }

    fun <T : Any> apiService(classType: Class<T>): T {
        return ApiClient.retrofit().create(classType)
    }
}