package com.livtech.common.core.network

import retrofit2.Response

/**
 * Subclass should extends this class to write their own network error parser and log the errors
 * also pass the instance of the subclass to @author to get the correct error
 * message
 */
open class ErrorMessageParser {
    open fun onNetworkError(t: Throwable): String {
        return t.localizedMessage as String
    }

    open fun <T> onApiCallFailed(response: Response<T>): String {
        return "Something went wrong"
    }
}