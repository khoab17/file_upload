package com.sharjahuniversity.type2dm_poc.data.model.uploadModel

import com.google.gson.annotations.SerializedName

data class SurveyAnswerUploadModelSurveyQuestion(
    @SerializedName("answer")
    val answer: String = "",
    @SerializedName("_id")
    val id: String,
    @SerializedName("options")
    val surveyAnswerUploadModelSurveyQuestionOption: List<SurveyAnswerUploadModelSurveyQuestionOption> = ArrayList(),
    @SerializedName("question_text")
    val questionText: String,
    @SerializedName("question_type")
    val questionType: String,
)