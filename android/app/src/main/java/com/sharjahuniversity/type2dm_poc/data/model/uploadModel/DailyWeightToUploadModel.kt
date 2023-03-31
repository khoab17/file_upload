package com.sharjahuniversity.type2dm_poc.data.model.uploadModel

import com.google.gson.annotations.SerializedName

data class DailyWeightToUploadModel(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("weight")
    val weight: Float
)