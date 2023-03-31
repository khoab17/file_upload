package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchUserResponse(
    @SerializedName("data")
    val fetchUserResponseData: FetchUserResponseData,
    @SerializedName("success")
    val success: Boolean
)