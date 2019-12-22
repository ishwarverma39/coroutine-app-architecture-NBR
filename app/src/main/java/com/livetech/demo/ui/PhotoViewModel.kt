package com.livetech.demo.ui

import androidx.lifecycle.MutableLiveData
import com.livetech.demo.core.AppConstants
import com.livetech.demo.core.PhotoRepo
import com.livetech.demo.core.models.Photo
import com.livetech.demo.core.models.PhotoData
import com.livtech.common.core.network.FAILED
import com.livtech.common.core.network.LOADING
import com.livtech.common.core.network.SUCCESS

class PhotoViewModel : BaseViewModel() {

    val photos = ArrayList<Photo>(0)
    val photoData = MutableLiveData<ArrayList<Photo>>()
    var page: Int = 1
    val repo = PhotoRepo(scope)
    var totalPages = 1
    var searchFor = ""
    val loading = MutableLiveData<Boolean>()

    fun fetchPhotos() {
        repo.getPhotos(getParams(searchFor)).observeForever { resource ->
            if (resource.data != null) {
                updateList(resource.data)
            }
            when (resource.status) {
                SUCCESS -> {
                    loading.value=false
                }
                FAILED -> {
                   loading.value=false
                    //todo show error message
                }
                LOADING ->{
                    loading.value=true
                }

            }
        }
    }

    private fun getParams(text: String): HashMap<String, Any> {
        return HashMap<String, Any>().apply {
            put(AppConstants.API_FORMAT_KEY, AppConstants.API_FORMAT_VALUE)
            put(AppConstants.API_METHOD_KEY, AppConstants.API_METHOD_VALUE)
            put(AppConstants.API_TEXT_KEY, text)
            put(AppConstants.API_JSON_CALLBACK_KEY, AppConstants.API_JSON_CALLBACK_VALUe)
            put(AppConstants.API_PER_PAGE_KEY, AppConstants.API_PER_PAGE_VALUE)
            put(AppConstants.API_PAGE_KEY, page)
        }
    }

    private fun updateList(data: PhotoData?) {
        if (data?.page == 1) {
            photos.clear()
        }
        totalPages = data?.pages as Int
        photos.addAll(data.photo as ArrayList<Photo>)
        photoData.value = photos
    }

    fun loadNextPage() {
        if (page < totalPages) {
            page++
            fetchPhotos()
        }
    }

    fun doSearching(searchText: String) {
        page = 1
        searchFor = searchText
        if (searchText.isEmpty())
            photoData.value = ArrayList()
        fetchPhotos()
    }
}