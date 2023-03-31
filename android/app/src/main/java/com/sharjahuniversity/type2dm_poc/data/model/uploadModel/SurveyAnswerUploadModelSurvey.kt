package com.sharjahuniversity.type2dm_poc.data.model.uploadModel

import com.google.gson.annotations.SerializedName

data class SurveyAnswerUploadModelSurvey(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("project_id")
    val projectId: String,
    @SerializedName("project_name")
    val projectName: String,
    @SerializedName("questions")
    val surveyAnswerUploadModelSurveyQuestion: List<SurveyAnswerUploadModelSurveyQuestion> = ArrayList(),
    @SerializedName("rating")
    val rating: Int,
)