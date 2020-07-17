package com.livetech.demo.core

import okhttp3.Interceptor
import okhttp3.Response

class AppInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter(AppConstants.API_KEY, apiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}