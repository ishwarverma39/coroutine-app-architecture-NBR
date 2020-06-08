package com.livtech.common.core.models

import com.livtech.common.core.network.*

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = "",
    @NetworkStatus val status: String,
    val error: ApiError? = null
) {
    class Success<out T>(data: T) :
        Resource<T>(data, "Request Successful", SUCCESS)

    class Loading<out T>(data: T? = null, message: String? = "Loading...") :
        Resource<T>(data, message, LOADING)

    class Failure<out T>(error: ApiError?, data: T? = null) :
        Resource<T>(data, error?.message, FAILED, error)
}