package com.livtech.common.core.network

import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class ErrorMessageParserImpl : ErrorMessageParser {

    override fun <T> onApiCallFailure(response: Response<T>): ApiError {
        if (response.code() == 200) {
            return ApiError(AE_404, "Data not found", false)
        } else if (response.code() == 500) {
            return ApiError(AE_500, "Internal server error", false)
        }
        return ApiError(AE_400, "Request is not correct", false)
    }

    override fun onNetworkFailure(throwable: Throwable): ApiError {
        if (throwable is IllegalArgumentException) {
            return ApiError(JSON_ERROR, "JSON parsing error")
        } else if (throwable is SocketTimeoutException || throwable is TimeoutException) {
            return ApiError(TIME_OUT, "Connection time out ")
        } else if (throwable is UnknownHostException) {
            return ApiError(NE_404, "Page not found")
        }
        return ApiError(UN_KNOWN_ERROR, "Something went wrong")
    }
}