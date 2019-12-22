package com.livtech.common.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

open class BaseViewModel :ViewModel(){

    private fun cancelCalls(){
        viewModelScope.coroutineContext.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        cancelCalls()
    }
}