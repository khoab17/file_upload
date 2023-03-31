package com.sharjahuniversity.type2dm_poc.data.model.responseModel

import com.google.gson.annotations.SerializedName

data class FetchExercisesResponseDataExerciseIcon(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("imagePath")
    val iconURL: String
)