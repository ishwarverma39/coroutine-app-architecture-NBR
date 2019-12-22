package com.livetech.demo.core.models

data class PhotoData(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: String,
    val photo: MutableList<Photo>
)