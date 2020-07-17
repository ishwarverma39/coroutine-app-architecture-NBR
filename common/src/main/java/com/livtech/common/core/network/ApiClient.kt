package com.livtech.common.core.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Authenticator
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
        private lateinit var httpClient: OkHttpClient
        private lateinit var retrofitClient: Retrofit

        fun initClient(
            baseURL: String,
            appInterceptor: Interceptor = DefaultInterceptor(),
            authenticator: Authenticator = DefaultAuthenticator(),
            networkInterceptor: Interceptor = DefaultNetworkInterceptor(),
            networkTimeOut: NetworkTimeOut = NetworkTimeOut()
        ) {
            baseUrl = baseURL
            httpClient = OkHttpClient()
                .newBuilder()
                .callTimeout(networkTimeOut.call, TimeUnit.SECONDS)
                .connectTimeout(networkTimeOut.connection, TimeUnit.SECONDS)
                .readTimeout(networkTimeOut.read, TimeUnit.SECONDS)
                .writeTimeout(networkTimeOut.write, TimeUnit.SECONDS)
                .addNetworkInterceptor(networkInterceptor)
                .authenticator(authenticator)
                .addInterceptor(appInterceptor)
                .build()

            retrofitClient = Retrofit.Builder()
                .client(httpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        val gson: Gson = GsonBuilder().setLenient().create()

        val retrofit
            get() = retrofitClient
    }
}