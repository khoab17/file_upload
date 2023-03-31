package com.sharjahuniversity.type2dm_poc.data.model.uploadModel

import com.google.gson.annotations.SerializedName

data class CalorieIntakeToUploadModelDataMacros(
    @SerializedName("carbs")
    val carbs: Float,
    @SerializedName("fat")
    val fat: Float,
    @SerializedName("sugar")
    val protein: Float
)