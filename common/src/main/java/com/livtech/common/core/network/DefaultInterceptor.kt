package com.livtech.common.core.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Application interceptors are added between the Application Code(our written code)
 * and the OkHttp Core Library
 * This interceptor is used to pass the header and some query params like access tokens, api-keys etc.
 * at single place and also for logging the errors centrally and showing the error message to the
 * view from single place
 */
class DefaultInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().let { chain.proceed(it) }
    }
}