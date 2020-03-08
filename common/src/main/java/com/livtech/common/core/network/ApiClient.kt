package com.livtech.common.core.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient private constructor() {

    companion object {
        private lateinit var baseUrl: String
        private lateinit var authInterceptor: Interceptor
        private lateinit var httpClient: OkHttpClient

        fun initClient(baseUrl: String, authInterceptor: Interceptor) {
            Companion.baseUrl = baseUrl
            Companion.authInterceptor = authInterceptor
            httpClient = OkHttpClient()
                .newBuilder()
                .callTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(Companion.authInterceptor)
                .build()
        }

        val gson = GsonBuilder().setLenient().create()

        fun retrofit(): Retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}