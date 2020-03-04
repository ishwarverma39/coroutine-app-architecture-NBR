package com.livetech.demo.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.livetech.demo.core.models.PhotoData
import com.livetech.demo.core.models.PhotoResponse
import com.livtech.common.core.models.BaseRepo
import com.livtech.common.core.models.Resource
import com.livtech.common.core.network.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch
import retrofit2.Response

class PhotoRepo(scope: CoroutineScope) : BaseRepo(scope) {
    var photoData: PhotoData?

    init {
        photoData = null
    }

    fun getPhotos(params: HashMap<String, Any>): LiveData<Resource<PhotoData?>> {
        photoData = null
        return object : NetworkBoundResource<PhotoData?, PhotoResponse>(
            true, scope, dispatcher
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
                    AppConstants.BASE_URL, params)
            }

        }.asLiveData


    }
}