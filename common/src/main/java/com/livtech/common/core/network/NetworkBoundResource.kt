package com.livtech.common.core.network

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.livtech.common.core.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val shouldLoad: Boolean = true,
    var call: suspend () -> Response<RequestType>,
    scope: CoroutineScope,
    private val errorParser: ErrorMessageParser = ErrorMessageParser()
) {
    private val result: LiveData<Resource<ResultType?>>

    init {
        result = liveData(scope.coroutineContext + Dispatchers.IO) {
            val disposable = emitSource(
                loadFromDb().map {
                    Resource.Loading(it, getLoadingMessage())
                }
            )
            if (shouldFetch()) {
                val res = makeApiCall()
                disposable.dispose()
                when (res.status) {
                    SUCCESS -> {
                        saveApiCallResponse(res.data)
                        emitSource(
                            loadFromDb().map {
                                Resource.Success(it)
                            }
                        )
                    }
                    FAILED -> {
                        emitSource(
                            loadFromDb().map {
                                Resource.Error(null, res.message)
                            }
                        )
                    }
                }
            } else {
                emitSource(
                    loadFromDb().map {
                        Resource.Success(it)
                    }
                )
            }
        }
    }

    private suspend fun makeApiCall(): Resource<RequestType?> {
        return try {
            call.invoke().let {
                if (it.isSuccessful) {
                    if (it.body() == null) {
                        Resource.Error(null, onApiCallFailed(it))
                    } else {
                        Resource.Success(it.body())
                    }
                } else {
                    Resource.Error(null, onApiCallFailed(it))
                }
            }
        } catch (t: Throwable) {
            Resource.Error(null, onNetworkError(t))
        }

    }

    val asLiveData: LiveData<Resource<ResultType?>>
        get() = result

    open fun shouldFetch(): Boolean {
        return shouldLoad
    }

    @WorkerThread
    abstract fun loadFromDb(): LiveData<ResultType>

    @WorkerThread
    abstract suspend fun saveApiCallResponse(response: RequestType?)

    @WorkerThread
    open fun onNetworkError(t: Throwable): String {
        return errorParser.onNetworkError(t)
    }

    @WorkerThread
    open fun onApiCallFailed(response: Response<RequestType>): String {
        return errorParser.onApiCallFailed(response)
    }

    /**
     * Subclasses should override this methods to show their own loading message
     */
    open fun getLoadingMessage(): String {
        return "Loading..."
    }
}