package com.livtech.common.core.models

import com.livtech.common.core.network.ApiError
import com.livtech.common.core.network.NetworkStatus

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = "",
    val status: NetworkStatus,
    val error: ApiError? = null
) {
    class Success<out T>(data: T) :
        Resource<T>(data, "Request Successful", NetworkStatus.SUCCESS)

    class Loading<out T>(data: T? = null, message: String? = "Loading...") :
        Resource<T>(data, message, NetworkStatus.LOADING)

    class Failure<out T>(error: ApiError?, data: T? = null) :
        Resource<T>(data, error?.message, NetworkStatus.FAILED, error)
}