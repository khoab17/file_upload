package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class UploadSurveyAnswerResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)