package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class DailyDataUploadResponseDataDayCalorieIntake(
    @SerializedName("calories")
    val calories: Int,
    @SerializedName("macros")
    val dailyDataUploadResponseDataDayCalorieIntakeMacros: DailyDataUploadResponseDataDayCalorieIntakeMacros
)