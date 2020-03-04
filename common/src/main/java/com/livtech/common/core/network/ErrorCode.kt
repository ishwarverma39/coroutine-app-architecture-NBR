package com.livtech.common.core.network

import androidx.annotation.StringDef

/*
NE => Network Error, if any error happens at network layer like page not found,
AE => App Error, When an error occurs at server-side while processing the request,
like record not found, or any exception happened at server side
JSON_ERROR => When error occurs while parsing the response as per the given response data
TIME_OUT => When sever is not responding within the given timeout duration
*/
const val AE_404 = "APP_404"
const val AE_400 = "APP_400"
const val AE_500 = "APP_500"
const val NE_404 = "NE_404"
const val NE_500 = "NE_500"
const val JSON_ERROR = "JSON_ERROR"
const val TIME_OUT = "TIME_OUT"
const val UN_KNOWN_ERROR = "UN_KNOWN_ERROR"

@StringDef(
    AE_404,
    AE_400,
    AE_500,
    NE_404,
    NE_500,
    JSON_ERROR,
    TIME_OUT,
    UN_KNOWN_ERROR
)
@Retention(AnnotationRetention.SOURCE)
annotation class ErrorCode