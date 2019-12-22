package com.livetech.demo.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class BaseViewModel :ViewModel(){
    private val parentJob = Job()

    val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    val scope = CoroutineScope(coroutineContext)

    fun cancelCalls(){
        coroutineContext.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        cancelCalls()
    }
}