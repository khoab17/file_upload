package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    val authResponseData: AuthResponseData,
    @SerializedName("status")
    val status: String
)