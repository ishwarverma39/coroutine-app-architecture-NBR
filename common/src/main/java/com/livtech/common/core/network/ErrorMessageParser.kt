package com.livtech.common.core.network

import retrofit2.Response

/**
 * Subclass should extends this class to write their own network error parser and log the errors
 * also pass the instance of the subclass to get the correct error
 * message
 */
interface ErrorMessageParser {
    /**
     * This method is used to parse the error which occurs at network level or
     * parsing the response to result type gets failed
     */
    fun onNetworkFailure(throwable: Throwable): ApiError

    /**
     * This method is used to parse the errors when the request reaches to the server
     * but there is an error due to bad request, data not found, or any exception happens
     * at server side.
     */
    fun <T> onApiCallFailure(response: Response<T>): ApiError
}