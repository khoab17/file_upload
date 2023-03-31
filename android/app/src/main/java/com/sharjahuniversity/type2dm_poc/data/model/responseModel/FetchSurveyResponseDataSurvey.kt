package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchSurveyResponseDataSurvey(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("questions")
    val fetchSurveyResponseDataSurveyQuestions: List<FetchSurveyResponseDataSurveyQuestion>,
    /*@SerializedName("rating")
    val rating: Int,*/
    @SerializedName("submittedAt")
    val submittedAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)