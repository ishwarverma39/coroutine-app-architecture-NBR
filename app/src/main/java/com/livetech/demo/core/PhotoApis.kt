package com.livetech.demo.core

import com.livetech.demo.core.models.PhotoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface PhotoApis {
    @GET
    fun getPhotosAsync(
        @Url url : String,
        @QueryMap params :HashMap<String,Any>) : Deferred<Response<PhotoResponse>>
}