package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchExercisesResponseDataExercise(
    @SerializedName("calories_per_minute")
    val caloriesPerMinute: Float,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("icon")
    val icon: FetchExercisesResponseDataExerciseIcon
)