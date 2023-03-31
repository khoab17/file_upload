package com.sharjahuniversity.type2dm_poc.data.model

import androidx.room.ColumnInfo

data class SurveyQuestions(
    val id: String,
    val question: String,
    val questionType: String,
    val options: List<String>,
    val answers: List<String>
)
