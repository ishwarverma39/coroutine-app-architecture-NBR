package com.livtech.common.core.network

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.livtech.common.core.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType>(
    val shouldLoad: Boolean = true,
    var call: suspend () -> Response<RequestType>,
    val scope: CoroutineScope
) {
    val result: MediatorLiveData<Resource<ResultType?>> = MediatorLiveData()

    init {
        result.apply {
            value = Resource.Loading(null, "Loading")
            also {
                scope.launch(Dispatchers.IO) {
                    val data = loadFromDb()
                    withContext(Dispatchers.Main) {
                        value =
                            Resource.Loading(
                                data,
                                "Loading"
                            )
                    }
                }
            }
            also {
                if (shouldFetch()) {
                    scope.launch(Dispatchers.IO) {
                        val res = makeApiCall()
                        when (res.status) {
                            SUCCESS -> {
                                saveApiCallResponse(res.data)
                                val data = loadFromDb()
                                withContext(Dispatchers.Main) {
                                    value =
                                        Resource.Success(
                                            data
                                        )
                                }
                            }
                            FAILED -> {
                                withContext(Dispatchers.Main) {
                                    value =
                                        Resource.Error(
                                            null,
                                            res.message
                                        )
                                }

                            }
                        }
                    }
                }else {
                    value = Resource.Success(null)
                }
            }
        }
    }

    private suspend fun makeApiCall(): Resource<RequestType?> {
        try {
            return call.invoke().let {
                if (it.isSuccessful) {
                    if (it.body() == null) {
                        onApiCallFailed("Something went wrong")
                        Resource.Error(
                            null,
                            "Something went wrong"
                        )
                    } else {
                        Resource.Success(it.body())
                    }
                } else {
                    onNetworkError(it.errorBody())
                    Resource.Error(null, it.message())
                }
            }
        } catch (e: Exception) {
            return Resource.Error(
                null,
                e.message
            )
        }

    }

    val asLiveData: LiveData<Resource<ResultType?>>
        get() = result

    open fun shouldFetch(): Boolean {
        return shouldLoad
    }

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    protected abstract suspend fun saveApiCallResponse(response: RequestType?)

    protected fun onNetworkError(error: ResponseBody?) {
        //todo need to log the error
    }

    protected fun onApiCallFailed(message: String) {
        //todo need to loa the error
    }
}