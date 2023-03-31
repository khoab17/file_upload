package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchSurveyResponseData(
    @SerializedName("survey")
    val fetchSurveyResponseDataSurveys: List<FetchSurveyResponseDataSurvey>
)