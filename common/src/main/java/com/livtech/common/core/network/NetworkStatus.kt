package com.livtech.common.core.network

import androidx.annotation.StringDef

const val LOADING = "loading"
const val SUCCESS = "success"
const val FAILED = "failed"
const val NO_INTERNET = "no internet"

@StringDef(
    LOADING,
    SUCCESS,
    FAILED,
    NO_INTERNET
)
@Retention(AnnotationRetention.SOURCE)
annotation class NetworkStatus

