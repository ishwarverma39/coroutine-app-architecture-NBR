package com.livetech.demo.core.models

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("photos")var photos : PhotoData,
    @SerializedName("stat")var stat :String)