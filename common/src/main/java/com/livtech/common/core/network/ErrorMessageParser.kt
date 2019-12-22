package com.livtech.common.core.network

import retrofit2.Response

/**
 * Subclass should extends this Class to write their own network error parser and log the errors
 * also pass the instance of the subclass to @NetworkBoundResource_New to get the correct message
 */
open class ErrorMessageParser {
    open fun onNetworkError(t: Throwable): String {
        return t.localizedMessage as String
    }

    open fun <T> onApiCallFailed(response: Response<T>): String {
        return "Something went wrong"
    }
}