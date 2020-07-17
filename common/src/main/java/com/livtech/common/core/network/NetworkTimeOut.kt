package com.livtech.common.core.network

/**
 * All time outs are in seconds, default time out is 60 seconds
 */
class NetworkTimeOut(
    val call: Long = 60,
    val read: Long = 60,
    val connection: Long = 60,
    val write: Long = 60
)
