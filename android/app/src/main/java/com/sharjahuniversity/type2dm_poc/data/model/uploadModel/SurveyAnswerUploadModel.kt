package com.sharjahuniversity.type2dm_poc.data.model.uploadModel

import com.google.gson.annotations.SerializedName

data class SurveyAnswerUploadModel(
    @SerializedName("survey")
    val surveyAnswerUploadModelSurvey: SurveyAnswerUploadModelSurvey,
    @SerializedName("user_id")
    val userId: String
)