package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class DailyDataUploadResponseDataDay(
    @SerializedName("calorie_expenditure")
    val calorieExpenditure: Int,
    @SerializedName("calorie_intake")
    val dailyDataUploadResponseDataDayCalorieIntake: DailyDataUploadResponseDataDayCalorieIntake,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("steps")
    val steps: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("water_intake")
    val waterIntake: Int
)