package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class DailyDataUploadResponse(
    @SerializedName("data")
    val dailyDataUploadResponseData: DailyDataUploadResponseData,
    @SerializedName("success")
    val success: Boolean
)