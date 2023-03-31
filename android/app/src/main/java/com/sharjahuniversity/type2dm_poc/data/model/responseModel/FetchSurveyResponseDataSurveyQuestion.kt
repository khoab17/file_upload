package com.sharjahuniversity.type2dm_poc.data.model.responseModel


import com.google.gson.annotations.SerializedName

data class FetchSurveyResponseDataSurveyQuestion(
    @SerializedName("added_by")
    val addedBy: String,
    @SerializedName("answer")
    val answer: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("options")
    val fetchSurveyResponseDataSurveyQuestionOptions: List<FetchSurveyResponseDataSurveyQuestionOption>,
    @SerializedName("question_text")
    val questionText: String,
    @SerializedName("question_type")
    val questionType: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)