package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchUserGoalsResponse(
    @SerializedName("data")
    val fetchUserGoalsResponseData: FetchUserGoalsResponseData,
    @SerializedName("success")
    val success: Boolean
)