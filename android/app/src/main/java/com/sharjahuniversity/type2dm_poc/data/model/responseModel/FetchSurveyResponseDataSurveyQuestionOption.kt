package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchSurveyResponseDataSurveyQuestionOption(
    @SerializedName("_id")
    val id: String,
    @SerializedName("selected")
    val selected: Boolean,
    @SerializedName("text")
    val text: String
)