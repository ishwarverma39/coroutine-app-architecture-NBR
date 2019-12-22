package com.livtech.common.core

import com.livtech.common.core.network.FAILED
import com.livtech.common.core.network.LOADING
import com.livtech.common.core.network.NetworkStatus
import com.livtech.common.core.network.SUCCESS

sealed class Resource<out T>(
    val data: T? = null,
    val message: String? = "", @NetworkStatus val status: String
) {
    class Success<out T>(data: T) : Resource<T>(
        data, "Request Successful",
        SUCCESS
    )

    class Loading<out T>(data: T? = null, message: String? = "") :
        Resource<T>(data, message, LOADING)

    class Error<out T>(data: T? = null, message: String?) :
        Resource<T>(data, message, FAILED)
}