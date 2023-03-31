package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchExercisesResponse(
    @SerializedName("data")
    val fetchExercisesResponseData: FetchExercisesResponseData,
    @SerializedName("success")
    val success: Boolean
)