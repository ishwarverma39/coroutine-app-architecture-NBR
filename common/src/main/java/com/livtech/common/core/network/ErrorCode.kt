package com.livtech.common.core.network

import androidx.annotation.StringDef

const val APP_404 = "APP_404"
const val APP_400 = "APP_400"
const val APP_500 = "APP_500"
const val NE_404 = "NE_404"
const val NE_500 = "NE_500"
const val JSON_ERROR = "JSON_ERROR"
const val TIME_OUT = "TIME_OUT"
const val UN_KNOWN_ERROR = "UN_KNOWN_ERROR"

@StringDef(
    APP_404,
    APP_400,
    APP_500,
    NE_404,
    NE_500,
    JSON_ERROR,
    TIME_OUT,
    UN_KNOWN_ERROR
)
@Retention(AnnotationRetention.SOURCE)
annotation class ErrorCode