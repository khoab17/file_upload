package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchExercisesResponseData(
    @SerializedName("count")
    val count: Int,
    @SerializedName("exercises")
    val fetchExercisesResponseDataExercises: List<FetchExercisesResponseDataExercise>
)