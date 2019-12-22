package com.livtech.common.core

import com.livtech.common.core.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response

open class BaseRepo (val scope: CoroutineScope){

    suspend fun <T> makeApiCall(call: suspend () -> Response<T>): Resource<T?> {
        val response = call.invoke()
        if (response.isSuccessful) return Resource.Success(
            response.body()
        )
        return Resource.Error(
            null,
            response.errorBody().toString()
        )
    }

    fun <T : Any> apiService(classType: Class<T>): T {
        return ApiClient.retrofit().create(classType)
    }
}