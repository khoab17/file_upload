package com.sharjahuniversity.type2dm_poc.data.model.uploadModel

import com.google.gson.annotations.SerializedName

data class CalorieIntakeToUploadModelData(
    @SerializedName("calories")
    val calories: Float,
    @SerializedName("macros")
    val calorieIntakeToUploadModelDataMacros: CalorieIntakeToUploadModelDataMacros
)