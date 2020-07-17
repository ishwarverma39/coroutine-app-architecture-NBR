package com.livtech.common.core.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Network interceptors are added between the OkHttp Core Library and the Server.
 *  These can be added to OkHttpClient using addNetworkInterceptor()
 *  This is mostly used to cache the response
 */
class DefaultNetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().let { chain.proceed(it) }
    }
}