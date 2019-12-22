package com.livetech.demo.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.livetech.demo.core.models.PhotoData
import com.livetech.demo.core.models.PhotoResponse
import com.livtech.common.core.BaseRepo
import com.livtech.common.core.Resource
import com.livtech.common.core.network.NetworkBoundResource
import kotlinx.coroutines.CoroutineScope

class PhotoRepo(scope: CoroutineScope) : BaseRepo(scope) {

    var photoData: PhotoData?

    init {
        photoData = null
    }

    fun getPhotos(params: HashMap<String, Any>): LiveData<Resource<PhotoData?>> {
        photoData = null
        return object : NetworkBoundResource<PhotoData?, PhotoResponse>(
            true,
            {
                apiService(PhotoApis::class.java).getPhotosAsync(AppConstants.BASE_URL, params)
                    .await()
            },
            scope
        ) {

            override fun loadFromDb(): LiveData<PhotoData?> {
                val data = MutableLiveData<PhotoData>()
                data.postValue(photoData)
                return data
            }

            override suspend fun saveApiCallResponse(response: PhotoResponse?) {
                photoData = response?.photos
            }

        }.asLiveData
    }
}