package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchSurveyResponse(
    @SerializedName("data")
    val fetchSurveyResponseData: FetchSurveyResponseData,
    @SerializedName("success")
    val success: Boolean
)