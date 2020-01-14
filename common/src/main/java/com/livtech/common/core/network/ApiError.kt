package com.livtech.common.core.network

data class ApiError(
    @ErrorCode val code: String,
    val message: String,
    val isNetworkError: Boolean = true
)
