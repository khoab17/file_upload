package com.sharjahuniversity.type2dm_poc.data.model.uploadModel

import com.google.gson.annotations.SerializedName

data class DailyExerciseToUploadModel(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("calorie_expenditure")
    val calorieExpenditure: Float
)