package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchFoodsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val fetchFoodsResponseData: FetchFoodsResponseData,
    @SerializedName("success")
    val success: Boolean
)