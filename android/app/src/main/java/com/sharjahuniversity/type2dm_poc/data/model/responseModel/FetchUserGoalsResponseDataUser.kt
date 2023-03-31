package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchUserGoalsResponseDataUser(
    @SerializedName("weight_goal")
    val weightGoal: Float,
    @SerializedName("calorie_intake_target")
    val calorieIntakeGoal: Float,
    @SerializedName("calorie_expenditure_target")
    val calorieBurnGoal: Float,
   @SerializedName("water_target")
    val waterGoal: Float,
    @SerializedName("step_target")
    val stepGoal: Float,
    @SerializedName("sleep_target")
    val sleepGoal: Float
)