package com.livtech.common.core.network

enum class NetworkStatus(val value: String) {
    LOADING("loading"),
    SUCCESS("success"),
    FAILED("failed"),
    NO_INTERNET("no_internet")
}