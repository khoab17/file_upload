package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class DailyDataUploadResponseDataDayCalorieIntakeMacros(
    @SerializedName("carbs")
    val carbs: Float,
    @SerializedName("fat")
    val fat: Float,
    @SerializedName("protein")
    val protein: Float
)