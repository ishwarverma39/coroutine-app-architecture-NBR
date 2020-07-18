package com.livetech.demo.ui

import androidx.lifecycle.MutableLiveData
import com.livetech.demo.core.AppConstants
import com.livetech.demo.core.PhotoRepo
import com.livetech.demo.core.models.Photo
import com.livetech.demo.core.models.PhotoData
import com.livtech.common.core.network.NetworkStatus
import com.livtech.common.ui.viewmodels.BaseViewModel

class PhotoViewModel(private val repo: PhotoRepo = PhotoRepo()) : BaseViewModel() {
    private val photos = ArrayList<Photo>(AppConstants.API_PER_PAGE_VALUE)
    val photoData = MutableLiveData<ArrayList<Photo>>()
    var page: Int = 1
    var totalPages = 1
    var searchFor = ""
    val loading = MutableLiveData<Boolean>()

    private fun fetchPhotos() {
        repo.getPhotos(getParams(searchFor)).observeForever { resource ->
            if (resource.data != null) {
                updateList(resource.data)
            }
            when (resource.status) {
                NetworkStatus.FAILED, NetworkStatus.SUCCESS -> {
                    loading.value = false
                    if (resource.status == NetworkStatus.FAILED) {
                        //todo show error message
                    }
                }
                NetworkStatus.LOADING -> {
                    loading.value = true
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