package com.livetech.demo.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.livetech.demo.core.models.PhotoData
import com.livetech.demo.core.models.PhotoResponse
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.NetworkBoundResource
import com.livtech.common.core.utils.DefaultDispatcherProvider
import com.livtech.common.core.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import retrofit2.Response

open class PhotoRepo(
    dispatcherProvider: DispatcherProvider = DefaultDispatcherProvider()
) : BaseRepo(dispatcherProvider) {
    var photoData: PhotoData?

    init {
        photoData = null
    }

    open fun getPhotos(params: HashMap<String, Any>): LiveData<Resource<PhotoData?>> {
        photoData = null
        return object : NetworkBoundResource<PhotoData?, PhotoResponse>(
            true, dispatcherProvider.io()
        ) {

            override fun loadFromDb(): LiveData<PhotoData?> {
                val data = MutableLiveData<PhotoData>()
                data.postValue(photoData)
                return data
            }

            override suspend fun saveApiCallResponse(response: PhotoResponse?) {
                photoData = response?.photos
            }

            override fun getRequestAsync(): Deferred<Response<PhotoResponse>> {
                return apiService(PhotoApis::class.java).getPhotosAsync(
                    AppConstants.BASE_URL, params
                )
            }

        }.asLiveData


    }
}