package com.livtech.common.core.network

data class ApiResponse<T>(
    val data: T,
    val message: String,
    val errors: Any,
    val status: Int
)
