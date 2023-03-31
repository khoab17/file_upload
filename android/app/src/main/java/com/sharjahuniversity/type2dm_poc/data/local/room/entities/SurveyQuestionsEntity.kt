package com.sharjahuniversity.type2dm_poc.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SurveyQuestionsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "question") val question: String,
    @ColumnInfo(name = "question_type") val questionType: String,
    @ColumnInfo(name = "options") val options: String,
    @ColumnInfo(name = "answers") val answers: String
)
